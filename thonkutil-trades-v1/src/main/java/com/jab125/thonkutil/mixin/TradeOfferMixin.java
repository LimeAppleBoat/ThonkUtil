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
    @Shadow public abstract NbtCompound toNbt();

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
