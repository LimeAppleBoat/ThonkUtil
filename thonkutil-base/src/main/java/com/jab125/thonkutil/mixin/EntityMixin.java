package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.thonkutil.api.events.server.entity.TotemUseEvent;
import com.jab125.thonkutil.impl.RegistryUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class EntityMixin implements RegistryUtil {
    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void thonkutil$tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (((LivingEntity)(Object)this).world.isClient) return;
        boolean shouldSavePlayer = (boolean) EventTaxi.executeEventTaxi(new TotemUseEvent((LivingEntity)(Object)this, source));
        System.out.println("Saving player: " + shouldSavePlayer);
        if (shouldSavePlayer) {
            cir.setReturnValue(true);
        }
    }
}
