package com.jab125.thonkutil.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityDataTrackerMixin extends LivingEntity {
    @Unique
    private String currentCosmetic = "";
    @Unique
    @Final
    private static final TrackedData<String> CURRENT_COSMETIC = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.STRING);

    protected PlayerEntityDataTrackerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void thonkutil$dataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(CURRENT_COSMETIC, "");
    }
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void thonkutil$writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putString("thonkutil:currentcosmetic", this.dataTracker.get(CURRENT_COSMETIC));
    }
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void thonkutil$readNbt(NbtCompound nbt, CallbackInfo ci) {
        this.dataTracker.set(CURRENT_COSMETIC, nbt.getString("thonkutil:currentcosmetic"));
    }
}
