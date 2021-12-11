package com.jab125.thonkutil.api.tradeoffer;

import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;

@Deprecated
@SuppressWarnings("unused")
public interface IdentifiableTrade extends com.jab125.thonkutil.api.IdentifiableTrade {
    Identifier getId();

    default boolean isSameType(com.jab125.thonkutil.api.IdentifiableTrade trade) {
        return trade.getId().equals(getId());
    }

    static boolean isSameType(com.jab125.thonkutil.api.IdentifiableTrade trade, com.jab125.thonkutil.api.IdentifiableTrade trade2) {
        return trade.getId().equals(trade2.getId());
    }

    static Identifier getIdOf(TradeOffer tradeOffer) {
        try {
            return ((com.jab125.thonkutil.api.IdentifiableTrade) tradeOffer).getId();
        } catch (ClassCastException e) {
            return new Identifier("minecraft", "trade_offer");
        }
    }
}
