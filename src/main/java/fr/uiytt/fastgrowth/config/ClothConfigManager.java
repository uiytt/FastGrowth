package fr.uiytt.fastgrowth.config;

import fr.uiytt.fastgrowth.FastGrowth;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;

public class ClothConfigManager {
    public static Screen build(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle("title." + FastGrowth.MODID +".config");
        ConfigCategory general = builder.getOrCreateCategory("dummyName");

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        general.addEntry(entryBuilder.startIntField("config."+FastGrowth.MODID + ".chanceOfFertilizing",FastGrowth.CONFIG_MANAGER.getChanceOfFertilizing())
                .setDefaultValue(33)
                .setTooltip("Chance to make a plant grow when shifting (between 0 and 100).")
                .setSaveConsumer(newValue -> {
                    FastGrowth.CONFIG_MANAGER.setChanceOfFertilizing(Math.max(0,Math.min(100,newValue)));
                })
                .setMax(100)
                .setMin(0)
                .build());
        general.addEntry(entryBuilder.startIntField("config."+FastGrowth.MODID + ".particleCount",FastGrowth.CONFIG_MANAGER.getParticleCount())
                .setDefaultValue(10)
                .setTooltip("Number of particle spawned when a plant grows")
                .setSaveConsumer(newValue -> {
                    FastGrowth.CONFIG_MANAGER.setParticleCount(newValue);
                })
                .setMin(0)
                .build());

        builder.setSavingRunnable(() -> {
            FastGrowth.CONFIG_MANAGER.saveConfig();
        });
        return builder.build();
    }
}
