package com.jab125.thonkutil.api.tradeoffer;

import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;

@SuppressWarnings("unused")
public interface IdentifiableTrade {
    Identifier getId();

    default boolean isSameType(IdentifiableTrade trade) {
        return trade.getId().equals(getId());
    }

    static boolean isSameType(IdentifiableTrade trade, IdentifiableTrade trade2) {
        return trade.getId().equals(trade2.getId());
    }

    static Identifier getIdOf(TradeOffer tradeOffer) {
        try {
            return ((IdentifiableTrade) tradeOffer).getId();
        } catch (ClassCastException e) {
            return new Identifier("minecraft", "trade_offer");
        }
    }
}
