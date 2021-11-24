package com.jab125.thonkutil.trades;

import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;


/**
 * Remapped by Jab125
 */
@Deprecated
public abstract class TradeSerializer<T extends ITradeType<? extends TradeOffers.Factory>>
{
    private Identifier id;

    public TradeSerializer(Identifier id)
    {
        this.id = id;
    }

    public Identifier getId()
    {
        return this.id;
    }

    public abstract T deserialize(JsonObject object);

    public JsonObject serialize(T trade)
    {
        JsonObject object = new JsonObject();
        object.addProperty("type", this.id.toString());
        return object;
    }
}
