package fr.uiytt.fastgrowth.mixin;

import fr.uiytt.fastgrowth.FastGrowth;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)

public class PlayerShiftEvent {

    @Inject(at = @At("HEAD"), method = "setSneaking(Z)V")
    private void sneakingActivate(boolean sneaking,CallbackInfo info) {
        if(!sneaking) {return;}
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        FastGrowth.playerShift(player);
    }

}
