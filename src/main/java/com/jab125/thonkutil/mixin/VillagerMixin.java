package com.jab125.thonkutil.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VillagerEntity.class)
public abstract class VillagerMixin {
//    VillagerEntity villagerEntity;
//    @Shadow public abstract VillagerData getVillagerData();
//
//    /**
//     * @author Jab125
//     */
//    @Overwrite
//    public void fillRecipes() {
//        villagerEntity = (VillagerEntity) (Object) this;
//        VillagerData villagerData = this.getVillagerData();
//        Int2ObjectMap<TradeOffers.Factory[]> int2ObjectMap = TradeOffers.PROFESSION_TO_LEVELED_TRADE.get(villagerData.getProfession());
//        if (int2ObjectMap != null && !int2ObjectMap.isEmpty()) {
//            TradeOffers.Factory[] factorys = int2ObjectMap.get(villagerData.getLevel());
//            if (factorys != null) {
//                TradeOfferList tradeOfferList = this.villagerEntity.getOffers();
//                this.villagerEntity.fillRecipesFromPool(tradeOfferList, factorys, 2);
//            }
//        }
//    }
//
//    public void populateTradeData() {
//        TradeOfferList offers = this.villagerEntity.getOffers();
//        @SuppressWarnings("unchecked")
//        EntityTrades entityTrades = TradeManager.instance().getTrades((EntityType<? extends VillagerEntity>) this.villagerEntity.getType());
//        if(entityTrades != null)
//        {
//            Map<TradeRarity, List<TradeOffers.Factory>> tradeMap = entityTrades.getTradeMap();
//            for(TradeRarity rarity : TradeRarity.values())
//            {
//                List<TradeOffers.Factory> trades = tradeMap.get(rarity);
//                int min = rarity.getMinimum().apply(trades, this.villagerEntity.getRandom());
//                int max = rarity.getMaximum().apply(trades, this.villagerEntity.getRandom());
//                this.addTrades(offers, trades, Math.max(min, max), rarity.shouldShuffle());
//            }
//        }
//    }
//    protected void addTrades(TradeOfferList offers, @Nullable List<TradeOffers.Factory> trades, int max, boolean shuffle)
//    {
//        if(trades == null)
//            return;
//        List<Integer> randomIndexes = IntStream.range(0, trades.size()).boxed().collect(Collectors.toList());
//        if(shuffle) Collections.shuffle(randomIndexes);
//        randomIndexes = randomIndexes.subList(0, Math.min(trades.size(), max));
//        for(Integer index : randomIndexes)
//        {
//            TradeOffers.Factory trade = trades.get(index);
//            TradeOffer offer = trade.create(this, this.villagerEntity.getRandom());
//            if(offer != null)
//            {
//                offers.add(offer);
//            }
//        }
//    }
//
//    public TradeOfferList getOffers()
//    {
//        if(this.villagerEntity.offers == null)
//        {
//            this.villagerEntity.offers = new TradeOfferList();
//            this.populateTradeData();
//        }
//        return this.offers;
//    }

}
