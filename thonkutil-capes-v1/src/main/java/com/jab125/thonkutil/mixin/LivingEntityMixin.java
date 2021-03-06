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
package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.api.CapeItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

import static com.jab125.thonkutil.util.Util.isModInstalled;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "getPreferredEquipmentSlot", at = @At(value = "HEAD"), cancellable = true)
    private static void getPreferredEquipmentSlot(ItemStack stack, CallbackInfoReturnable<EquipmentSlot> cir) {
        if (isModInstalled("trinkets")) return; // TODO: Trinkets compat
        Item item = stack.getItem();

        if (item instanceof CapeItem) {
            cir.setReturnValue(EquipmentSlot.CHEST);
        }

    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void onDeathInject(DamageSource source, CallbackInfo ci) {
        if (((LivingEntity) (Object) this).world.isClient()) return;
        var a = pickOutCape();
        if (Math.random() * 100 == 0)
            ((LivingEntity) (Object) this).dropStack(new ItemStack(a));
        //((LivingEntity)(Object) this).world.getServer().sendSystemMessage(new LiteralText("You got " + I18n.translate(a.getTranslationKey()) + "!"), Util.NIL_UUID);
    }

    private Item pickOutCape() {
        boolean a = false;
        if (((LivingEntity) (Object) this).getType().equals(EntityType.ZOMBIE)) a = true;
        if (((LivingEntity) (Object) this).getType().equals(EntityType.HUSK)) a = true;
        if (((LivingEntity) (Object) this).getType().equals(EntityType.DROWNED)) a = true;
        if (((LivingEntity) (Object) this).getType().equals(EntityType.ZOMBIFIED_PIGLIN)) a = true;
        if (((LivingEntity) (Object) this).getType().equals(EntityType.SKELETON)) a = true;
        if (((LivingEntity) (Object) this).getType().equals(EntityType.STRAY)) a = true;
        if (!a) return Items.AIR;
        ArrayList<Pair<Item, Integer>> capCapes = new ArrayList<>();
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:migration_cape")), 50));
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:founders_cape")), 25));
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:pan_cape")), 25));
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:new_year_2011_cape")), 15));
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:minecon_2016_cape")), 7));
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:minecon_2015_cape")), 5));
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:minecon_2013_cape")), 3));
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:minecon_2012_cape")), 2));
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:minecon_2011_cape")), 1));
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:translator_cape")), 1));
        capCapes.add(new Pair<>(Registry.ITEM.get(new Identifier("thonkutil:animated_christmas_2010_cape")), 1));
        final Object[] c = capCapes.toArray();
        final int b = (int) (Math.random() * c.length);
        return ((Pair<Item, Integer>) c[b]).getRight() >= (Math.random() * 100) + 1 ? ((Pair<Item, Integer>) c[b]).getLeft() : Items.AIR;
    }
}
