package com.jab125.thonkutil.trades;

import com.google.gson.JsonObject;
import net.minecraft.village.TradeOffers;

/**
 * Remapped by Jab125
 */
@Deprecated
public interface ITradeType<T extends TradeOffers.Factory>
{
    JsonObject serialize();

    T createTradeOffer();
}
