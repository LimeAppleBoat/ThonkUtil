package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.config.ThonkUtilCustomizationConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VillagerEntity.class)
public class VillagerMixin extends MobEntity {
    protected VillagerMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return ThonkUtilCustomizationConfig.LEAD_VILLAGERS.getValue();
    }
}
