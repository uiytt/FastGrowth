package fr.uiytt.fastgrowth;

import fr.uiytt.fastgrowth.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FastGrowth implements ModInitializer {

    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir();
    public static final ConfigManager CONFIG_MANAGER = new ConfigManager(CONFIG_PATH);
    public static final String MODID = "fastgrowth";


    @Override
    public void onInitialize() {
        CONFIG_MANAGER.load();
    }

    public static void playerShift(PlayerEntity player) {
        BlockPos player_pos = player.getBlockPos();
        World world = player.getEntityWorld();
        ItemStack bonemeal = new ItemStack(Items.BONE_MEAL);
        List<BlockPos> fertilizable = new ArrayList<>();
        int y = (int) Math.round(player.getPos().getY());
        int player_x = player_pos.getX();
        int player_z = player_pos.getZ();
        //Loop through around blocks
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) {
                BlockPos crop_pos = new BlockPos(player_x+x,y,player_z+z);
                BlockState blockState = world.getBlockState(crop_pos);
                //Check if the block can grow
                if(blockState.getBlock() instanceof Fertilizable) {
                    fertilizable.add(crop_pos);
                }
            }
        }
        int randomChance = ThreadLocalRandom.current().nextInt(1,100);
        if(randomChance > CONFIG_MANAGER.getChanceOfFertilizing()) {
            return;
        }
        if(fertilizable.size() > 0) {
            //Take random growable block, and grows it.
            BlockPos crop_pos = fertilizable.get(ThreadLocalRandom.current().nextInt(fertilizable.size()));
            BoneMealItem.useOnFertilizable(bonemeal,world,crop_pos);
            if(CONFIG_MANAGER.getParticleCount() > 0) {
                ParticleS2CPacket particle_packet = new ParticleS2CPacket(
                        ParticleTypes.HAPPY_VILLAGER,
                        false,
                        crop_pos.getX() + 0.5,
                        crop_pos.getY() + 0.5,
                        crop_pos.getZ() + 0.5,
                        0.2F,
                        0.2F,
                        0.2F,
                        1.0F,
                        CONFIG_MANAGER.getParticleCount());

                ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, particle_packet);
            }

        }
    }


}
