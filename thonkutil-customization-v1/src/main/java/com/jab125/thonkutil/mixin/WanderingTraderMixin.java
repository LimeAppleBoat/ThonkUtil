package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.config.ThonkUtilCustomizationConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WanderingTraderEntity.class)
public class WanderingTraderMixin extends MobEntity {
    protected WanderingTraderMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return ThonkUtilCustomizationConfig.LEAD_WANDERING_TRADERS.getValue();
    }
}
