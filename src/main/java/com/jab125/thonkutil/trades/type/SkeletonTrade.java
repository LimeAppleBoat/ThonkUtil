package com.jab125.thonkutil.trades.type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jab125.thonkutil.forgehelper.CraftingHelper;
import com.jab125.thonkutil.trades.StandardTrade;
import com.jab125.thonkutil.trades.ITradeType;
import com.jab125.thonkutil.trades.TradeSerializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Remapped by Jab125
 */
@Deprecated
public class SkeletonTrade implements ITradeType<StandardTrade>
{
    public static final Serializer SERIALIZER = new Serializer();

    private final ItemStack offerStack;
    private final ItemStack paymentStack;
    private final ItemStack secondaryPaymentStack;
    private final float priceMultiplier;
    private final int maxTrades;
    private final int experience;

    public SkeletonTrade(ItemStack offerStack, ItemStack paymentStack, ItemStack secondaryPaymentStack, float priceMultiplier, int maxTrades, int experience)
    {
        this.offerStack = offerStack;
        this.paymentStack = paymentStack;
        this.secondaryPaymentStack = secondaryPaymentStack;
        this.priceMultiplier = priceMultiplier;
        this.maxTrades = maxTrades;
        this.experience = experience;
    }

    @Override
    public StandardTrade createTradeOffer()
    {
        ItemStack offerStack = this.offerStack.copy();
        return new StandardTrade(offerStack, this.paymentStack.copy(), this.secondaryPaymentStack.copy(), this.maxTrades, this.experience, this.priceMultiplier);
    }

    @Override
    public JsonObject serialize()
    {
        return SERIALIZER.serialize(this);
    }

    public static class Serializer extends TradeSerializer<SkeletonTrade>
    {
        Serializer()
        {
            super(new Identifier("thonkutil", "skeleton"));
        }

        public SkeletonTrade fromTradeOffer(TradeOffer tradeOffer) {
            return new SkeletonTrade(tradeOffer.getSellItem().copy(), tradeOffer.getOriginalFirstBuyItem().copy(), tradeOffer.getSecondBuyItem(), tradeOffer.getPriceMultiplier(), tradeOffer.getMaxUses(), tradeOffer.getMerchantExperience());
        }

        @Override
        public SkeletonTrade deserialize(JsonObject object)
        {
            Builder builder = Builder.create();
            builder.setOfferStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "offer_item"), true));
            builder.setPaymentStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "payment_item"), true));
            if(JsonHelper.hasElement(object, "secondary_payment_item"))
            {
                builder.setSecondaryPaymentStack(CraftingHelper.getItemStack(JsonHelper.getObject(object, "secondary_payment_item"), true));
            }
            builder.setPriceMultiplier(JsonHelper.getFloat(object, "price_multiplier", 0.05F));
            builder.setMaxTrades(JsonHelper.getInt(object, "max_trades", 12));
            builder.setExperience(JsonHelper.getInt(object, "experience", 0));
            return builder.build();
        }

        @Override
        public JsonObject serialize(SkeletonTrade trade)
        {
            JsonObject object = super.serialize(trade);
            object.add("offer_item", this.serializeItemStack(trade.offerStack));
            object.add("payment_item", this.serializeItemStack(trade.paymentStack));
            if(!trade.secondaryPaymentStack.isEmpty())
            {
                object.add("secondary_payment_item", this.serializeItemStack(trade.secondaryPaymentStack));
            }
            if(trade.priceMultiplier != 0.05F)
            {
                object.addProperty("price_multiplier", trade.priceMultiplier);
            }
            if(trade.maxTrades != 12)
            {
                object.addProperty("max_trades", trade.maxTrades);
            }
            if(trade.experience != 0)
            {
                object.addProperty("experience", trade.experience);
            }
            return object;
        }

        private JsonObject serializeItemStack(ItemStack stack)
        {
            JsonObject object = new JsonObject();
            object.addProperty("item", Objects.requireNonNull(Registry.ITEM.getId(stack.getItem())).toString());
            object.addProperty("count", stack.getCount());
            if(stack.hasNbt())
            {
                object.addProperty("nbt", Objects.requireNonNull(stack.getNbt()).toString());
            }
            return object;
        }

        private JsonObject serializeEnchantment(EnchantmentLevelEntry enchantment)
        {
            JsonObject object = new JsonObject();
            object.addProperty("id", Objects.requireNonNull(Registry.ENCHANTMENT.getId(enchantment.enchantment)).toString());
            object.addProperty("level", enchantment.level);
            return object;
        }

        private JsonObject serializePotionEffect(StatusEffectInstance effect)
        {
            JsonObject object = new JsonObject();
            object.addProperty("id", Objects.requireNonNull(Registry.STATUS_EFFECT.getId(effect.getEffectType())).toString());
            object.addProperty("duration", effect.getDuration());
            object.addProperty("amplifier", effect.getAmplifier());
            object.addProperty("show_particles", effect.shouldShowParticles());
            return object;
        }

        private Collection<EnchantmentLevelEntry> getEnchantments(JsonArray enchantmentArray)
        {
            List<EnchantmentLevelEntry> enchantments = new ArrayList<>();
            for(JsonElement enchantmentElement : enchantmentArray)
            {
                JsonObject enchantmentObject = enchantmentElement.getAsJsonObject();
                String id = JsonHelper.getString(enchantmentObject, "id");
                Enchantment enchantment = Registry.ENCHANTMENT.get(new Identifier(id));
                if(enchantment != null)
                {
                    int level = JsonHelper.getInt(enchantmentObject, "level", 1);
                    enchantments.add(new EnchantmentLevelEntry(enchantment, level));
                }
            }
            return enchantments;
        }

        private Collection<StatusEffectInstance> getPotionEffects(JsonArray effectsArray)
        {
            List<StatusEffectInstance> effects = new ArrayList<>();
            for(JsonElement effectElement : effectsArray)
            {
                JsonObject effectObject = effectElement.getAsJsonObject();
                String id = JsonHelper.getString(effectObject, "id");
                StatusEffect effect = Registry.STATUS_EFFECT.get(new Identifier(id));
                if(effect != null)
                {
                    int duration = JsonHelper.getInt(effectObject, "duration", 1);
                    int amplifier = JsonHelper.getInt(effectObject, "amplifier", 1);
                    boolean showParticles = JsonHelper.getBoolean(effectObject, "show_particles", true);
                    effects.add(new StatusEffectInstance(effect, duration, amplifier, false, showParticles));
                }
            }
            return effects;
        }
    }

    public static class Builder
    {
        private ItemStack offerStack;
        private ItemStack paymentStack;
        private ItemStack secondaryPaymentStack = ItemStack.EMPTY;
        private float priceMultiplier = 0.0F;
        private int maxTrades = 12;
        private int experience = 10;

        private Builder() {}

        public static Builder create()
        {
            return new Builder();
        }

        public SkeletonTrade build()
        {
            return new SkeletonTrade(this.offerStack, this.paymentStack, this.secondaryPaymentStack, this.priceMultiplier, this.maxTrades, this.experience);
        }

        public Builder setOfferStack(ItemStack offerStack)
        {
            this.offerStack = offerStack;
            return this;
        }

        public Builder setPaymentStack(ItemStack paymentStack)
        {
            this.paymentStack = paymentStack;
            return this;
        }

        public Builder setSecondaryPaymentStack(ItemStack secondaryPaymentStack)
        {
            this.secondaryPaymentStack = secondaryPaymentStack;
            return this;
        }

        public Builder setPriceMultiplier(float priceMultiplier)
        {
            this.priceMultiplier = priceMultiplier;
            return this;
        }

        public Builder setMaxTrades(int maxTrades)
        {
            this.maxTrades = maxTrades;
            return this;
        }

        @Deprecated
        public Builder setExperience(int experience)
        {
            return this.setMerchantExperience(experience);
        }

        public Builder setMerchantExperience(int merchantExperience)
        {
            this.experience = merchantExperience;
            return this;
        }

        public Builder setPlayerExperience(int playerExperience)
        {
            return this;
        }
    }
}
