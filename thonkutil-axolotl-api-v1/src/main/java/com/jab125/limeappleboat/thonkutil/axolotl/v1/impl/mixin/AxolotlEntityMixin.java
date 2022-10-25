/*
 * Copyright (c) 2021, 2022 Jab125 & LimeAppleBoat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.mixin;

import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.AxolotlTypeExtension;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AxolotlEntity.class)
public abstract class AxolotlEntityMixin extends LivingEntity {
    @Shadow @Final private Map<String, Vec3f> modelAngles;

    @Shadow public abstract AxolotlEntity.Variant getVariant();

    @Shadow @Final public static String VARIANT_KEY;

    @Shadow protected abstract void setVariant(AxolotlEntity.Variant variant);

    @Unique
    private static final TrackedData<String> thonkutil$VARIANT = DataTracker.registerData(AxolotlEntity.class, TrackedDataHandlerRegistry.STRING);


    protected AxolotlEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void thonkutil$initTrackers(CallbackInfo ci) {
        this.dataTracker.startTracking(thonkutil$VARIANT, "minecraft:lucy");
    }

    @Inject(method = "getVariant", at = @At("HEAD"), cancellable = true)
    public void getVariant(CallbackInfoReturnable<AxolotlEntity.Variant> cir) {
        for (AxolotlEntity.Variant variant : AxolotlEntity.Variant.VARIANTS) {
            var metadata = ((AxolotlTypeExtension)(Object)variant).thonkutil$thonkutil_metadata();
            if (metadata.getId().toString().equals(this.dataTracker.get(thonkutil$VARIANT))) {
                cir.setReturnValue(variant);
                break;
            }
        }
        //return AxolotlEntity.Variant.VARIANTS[this.dataTracker.get(VARIANT)];
    }

    @Inject(method = "setVariant", at = @At("HEAD"))
    private void setVariant(AxolotlEntity.Variant variant, CallbackInfo ci) {
        var metadata = ((AxolotlTypeExtension)(Object)variant).thonkutil$thonkutil_metadata();
        this.dataTracker.set(thonkutil$VARIANT, metadata.getId().toString());
        //this.dataTracker.set(VARIANT, variant.getId());
    }

    @Redirect(method = "copyDataToStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;putInt(Ljava/lang/String;I)V", ordinal = 0))
    private void saveAsString(NbtCompound instance, String key, int value) {
        instance.putString(key, ((AxolotlTypeExtension)(Object)getVariant()).thonkutil$thonkutil_metadata().getId().toString());
    }

    @Redirect(method = "copyDataFromNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AxolotlEntity;setVariant(Lnet/minecraft/entity/passive/AxolotlEntity$Variant;)V", ordinal = 0))
    private void thonkutil$redirectNbt(AxolotlEntity instance, AxolotlEntity.Variant variant) {

    }

    @Redirect(method = "copyDataFromNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;getInt(Ljava/lang/String;)I", ordinal = 0))
    private int thonkutil$redirectNbt2(NbtCompound instance, String key) {
        return 0;
    }

    @Inject(method = "copyDataFromNbt", at = @At(value = "RETURN"))
    private void thonkutil$useStringInstead(NbtCompound nbt, CallbackInfo ci) {
        try {
            if (nbt.contains(VARIANT_KEY, NbtElement.INT_TYPE)) {
                var i = nbt.getInt(VARIANT_KEY);
                if (i >= 0 && i < AxolotlEntity.Variant.VARIANTS.length) {
                    nbt.remove(VARIANT_KEY);
                    nbt.putString(VARIANT_KEY, ((AxolotlTypeExtension) (Object) AxolotlEntity.Variant.VARIANTS[i]).thonkutil$thonkutil_metadata().getId().toString());
                } else {
                    nbt.putString(VARIANT_KEY, "minecraft:lucy");
                }
            }
            for (AxolotlEntity.Variant variant : AxolotlEntity.Variant.VARIANTS) {
                if (((AxolotlTypeExtension) (Object) variant).thonkutil$thonkutil_metadata().getId().equals(new Identifier(nbt.getString(VARIANT_KEY)))) {
                    this.setVariant(variant);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.setVariant(AxolotlEntity.Variant.LUCY);
        }
    }

    @Redirect(method = "writeCustomDataToNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;putInt(Ljava/lang/String;I)V", ordinal = 0))
    private void thonkutil$writeCustomDataToNbt(NbtCompound instance, String key, int value) {
        instance.putString(VARIANT_KEY, ((AxolotlTypeExtension)(Object)this.getVariant()).thonkutil$thonkutil_metadata().getId().toString());
    }

    @Unique
    private NbtCompound nbt;
    @Inject(method = "readCustomDataFromNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AnimalEntity;readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", shift = At.Shift.AFTER))
    private void thonkutil$readCustomDataFromNbtCapture(NbtCompound nbt, CallbackInfo ci) {
        this.nbt = nbt;
    }

    @Redirect(method = "readCustomDataFromNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AxolotlEntity;setVariant(Lnet/minecraft/entity/passive/AxolotlEntity$Variant;)V", ordinal = 0))
    private void thonkutil$readCustomDataFromNbt(AxolotlEntity instance, AxolotlEntity.Variant variant) {
        try {
            if (nbt.contains(VARIANT_KEY, NbtElement.INT_TYPE)) {
                var i = nbt.getInt(VARIANT_KEY);
                if (i >= 0 && i < AxolotlEntity.Variant.VARIANTS.length) {
                    nbt.remove(VARIANT_KEY);
                    nbt.putString(VARIANT_KEY, ((AxolotlTypeExtension) (Object) AxolotlEntity.Variant.VARIANTS[i]).thonkutil$thonkutil_metadata().getId().toString());
                } else {
                    nbt.putString(VARIANT_KEY, "minecraft:lucy");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            nbt.putString(VARIANT_KEY, "minecraft:lucy");
        }
        for (AxolotlEntity.Variant variant1 : AxolotlEntity.Variant.VARIANTS) {
            if (((AxolotlTypeExtension)(Object)variant1).thonkutil$thonkutil_metadata().getId().equals(new Identifier(nbt.getString(VARIANT_KEY)))) {
                this.setVariant(variant1);
                break;
            }
        }
    }
}
