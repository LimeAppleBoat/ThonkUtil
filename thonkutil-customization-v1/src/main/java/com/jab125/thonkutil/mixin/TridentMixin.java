package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.ThonkUtilCustomization;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TridentEntity.class)
public abstract class TridentMixin extends PersistentProjectileEntity {
    @Shadow
    @Final
    private static TrackedData<Byte> LOYALTY;
    @Unique
    @Final
    private static final TrackedData<Byte> VOID_SAVER = DataTracker.registerData(TridentEntity.class, TrackedDataHandlerRegistry.BYTE);
    ;

    @Shadow
    private boolean dealtDamage;

    @Unique
    private int thonkutil_voidRelease = 0;

    protected TridentMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void thonkutil$injectDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(VOID_SAVER, (byte) 0);
    }


    @Override
    protected void tickInVoid() {
        if (dataTracker.get(LOYALTY) > 0 || dataTracker.get(VOID_SAVER) > 0) {
            this.dealtDamage = true;
            this.thonkutil_voidRelease = 1;
            //this.thonkutil_voidRelease = 1;
        }
        if (this.thonkutil_voidRelease == 1) return;
        super.tickInVoid();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void thonkutil$injectTick(CallbackInfo ci) {

    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)V", at = @At("TAIL"))
    private void thonkutil$injectInit(World world, LivingEntity owner, ItemStack stack, CallbackInfo ci) {
        this.dataTracker.set(VOID_SAVER, (byte) EnchantmentHelper.getLevel(ThonkUtilCustomization.VOID_SAVER, stack));
    }

    //private int thonkutil$modifyI(DataTracker instance, TrackedData<T> data) {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/data/DataTracker;get(Lnet/minecraft/entity/data/TrackedData;)Ljava/lang/Object;"))
    private <T> T thonkutil$modifyI(DataTracker instance, TrackedData<T> data) {
        if (data.equals(LOYALTY) && this.thonkutil_voidRelease == 1) {
            if (instance.get(VOID_SAVER) > 0) {
                return (T) (Object) max(instance.get(VOID_SAVER), instance.get(LOYALTY));
            }
        }
        return instance.get(data);
    }

    private static byte max(byte a, byte b) {
        return (a >= b) ? a : b;
    }
}
