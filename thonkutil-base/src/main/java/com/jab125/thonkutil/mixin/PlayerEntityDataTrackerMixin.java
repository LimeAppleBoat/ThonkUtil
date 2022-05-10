package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.cosmetics.Cosmetics;
import com.jab125.thonkutil.impl.PlayerExpansion;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.jab125.thonkutil.cosmetics.Cosmetics.cosmetics;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityDataTrackerMixin extends LivingEntity implements PlayerExpansion {
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow public abstract void remove(RemovalReason reason);

    @Unique
    @Final
    private static final TrackedData<String> CURRENT_COSMETIC = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.STRING);

    @Unique
    @Final
    private static final TrackedData<Boolean> COSMETIC_ENCHANTED = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected PlayerEntityDataTrackerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void thonkutil$dataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(CURRENT_COSMETIC, "");
        this.dataTracker.startTracking(COSMETIC_ENCHANTED, false);
    }
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void thonkutil$writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putString("thonkutil:currentcosmetic", this.dataTracker.get(CURRENT_COSMETIC));
        nbt.putBoolean("thonkutil:cosmeticenchanted", this.dataTracker.get(COSMETIC_ENCHANTED));
    }
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void thonkutil$readNbt(NbtCompound nbt, CallbackInfo ci) {
        this.dataTracker.set(CURRENT_COSMETIC, nbt.getString("thonkutil:currentcosmetic"));
        this.dataTracker.set(COSMETIC_ENCHANTED, nbt.getBoolean("thonkutil:cosmeticenchanted"));
    }

    @Override
    public String thonkutil$getCosmetic() {
        if (thonkutil$ownsCosmetic(this.dataTracker.get(CURRENT_COSMETIC)))
            return this.dataTracker.get(CURRENT_COSMETIC);
        return "";
    }

    @Override
    public void thonkutil$setCosmetic(String string) {
        if (thonkutil$ownsCosmetic(string))
            this.dataTracker.set(CURRENT_COSMETIC, string);
    }

    @Override
    public boolean thonkutil$cosmeticEnchanted() {
        return this.dataTracker.get(COSMETIC_ENCHANTED) && thonkutil$cosmeticEnchantable(this.thonkutil$getCosmetic());
    }

    @Override
    public boolean thonkutil$cosmeticEnchantable(String cosmetic) {
        for (Cosmetics.CosmeticType cosmetic2 : cosmetics) {
            if (cosmetic2.uuid().equals(this.uuidString) || (cosmetic2.uuid().equals("dev") && FabricLoader.getInstance().isDevelopmentEnvironment())) {
                for (Pair<String, Boolean> cosmetic1 : cosmetic2.cosmetics()) {
                    if (cosmetic1.getLeft().equals(cosmetic)) {
                        return cosmetic1.getRight();
                    }
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean thonkutil$ownsCosmetic(String string) {
        if (string.equals("")) return true;
        for (Cosmetics.CosmeticType cosmetic : cosmetics) {
            if (cosmetic.uuid().equals(this.uuidString) || (cosmetic.uuid().equals("dev") && FabricLoader.getInstance().isDevelopmentEnvironment())) {
                for (Pair<String, Boolean> cosmetic1 : cosmetic.cosmetics()) {
                    if (cosmetic1.getLeft().equals(string)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public void thonkutil$setEnchanted(boolean enchant) {
        if (thonkutil$cosmeticEnchantable(thonkutil$getCosmetic()))
            this.dataTracker.set(COSMETIC_ENCHANTED, enchant);
    }
}
