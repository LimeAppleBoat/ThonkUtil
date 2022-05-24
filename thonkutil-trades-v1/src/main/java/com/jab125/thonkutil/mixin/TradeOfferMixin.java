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

import com.jab125.thonkutil.api.IdentifiableTrade;
import com.jab125.thonkutil.config.ThonkUtilTradeConfig;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TradeOffer.class)
public abstract class TradeOfferMixin implements IdentifiableTrade {
    @Shadow
    public abstract NbtCompound toNbt();

    @Override
    public Identifier getId() {
        return new Identifier("minecraft", "trade_offer");
    }

    @ModifyVariable(method = "toNbt", at = @At("STORE"))
    public NbtCompound modifyToNbt(NbtCompound nbtCompound) {
        if (ThonkUtilTradeConfig.TRADE_OFFER_ID.getValue()) {
            if (!this.getId().equals(new Identifier("minecraft:trade_offer"))) return nbtCompound;
            nbtCompound.putString("id", getId().toString());
        }
        return nbtCompound;
    }
}
