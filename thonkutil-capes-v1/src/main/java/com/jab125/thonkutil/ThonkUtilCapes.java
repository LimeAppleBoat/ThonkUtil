package com.jab125.thonkutil;

import com.jab125.thonkutil.api.AnimatedCapeItem;
import com.jab125.thonkutil.api.CapeItem;
import com.jab125.thonkutil.impl.CapeCommand;
import com.jab125.thonkutil.util.Util;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.Optional;

public class ThonkUtilCapes implements ModInitializer, ThonkUtilCapesClass {
    public static ItemGroup CAPES_GROUP = FabricItemGroupBuilder.create(Identifier.tryParse("thonkutil:capes")).icon(() -> new ItemStack(Registry.ITEM.get(new Identifier("thonkutil:minecon_2011_cape")))).build();

    @Override
    public void onInitialize() {
        System.out.println(this.modId() + " Init");
        registerCapes();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            CapeCommand.register(dispatcher);
        });
    }

    private static void registerCapes() {
        Util.quickRegisterItem(new Identifier("thonkutil:mojang_classic_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:mojang_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:mojang_studios_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));

        Util.quickRegisterItem(new Identifier("thonkutil:minecon_2011_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:minecon_2012_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:minecon_2013_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:minecon_2015_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:minecon_2016_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));

        Util.quickRegisterItem(new Identifier("thonkutil:bacon_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE), false));
        Util.quickRegisterItem(new Identifier("thonkutil:one_millionth_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:dannybstyle_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:julian_clark_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:mrmessiah_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:prismarine_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:birthday_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));

        Util.quickRegisterItem(new Identifier("thonkutil:translator_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:chinese_translator_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:japanese_translator_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:scrolls_champion_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:cobalt_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:mojira_moderator_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:mapmaker_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:turtle_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:migration_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));

        Util.quickRegisterItem(new Identifier("thonkutil:founders_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));
        Util.quickRegisterItem(new Identifier("thonkutil:pan_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));

        Util.quickRegisterItem(new Identifier("thonkutil:hero_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE), false));

        Util.quickRegisterItem(new Identifier("thonkutil:christmas_2010_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE), false));
        Util.quickRegisterItem(new Identifier("thonkutil:new_year_2011_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE), false));
        Util.quickRegisterItem(new Identifier("thonkutil:first_birthday_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE), false));

        Util.quickRegisterItem(new Identifier("thonkutil:optifine_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE)));

        Util.quickRegisterItem(new Identifier("thonkutil:better_optifine_cape"), new CapeItem(new Item.Settings().rarity(Rarity.RARE), true, true));

        Util.quickRegisterItem(new Identifier("thonkutil:animated_christmas_2010_cape"), new AnimatedCapeItem(new Item.Settings().rarity(Rarity.RARE), 2, 1000, false));
    }

    public static String createItemModelJson(Identifier id, String type) {
        if ("cape".equals(type)) {
            return "{ \"texture_size\": [64, 32], \"textures\": { \"0\": \"" + id.toString() + "\", \"particle\": \"" + id.toString() + "\" }, \"elements\": [ { \"from\": [3, 0, 0], \"to\": [13, 1, 16], \"faces\": { \"north\": {\"uv\": [0.25, 0, 2.75, 0.5], \"texture\": \"#0\"}, \"east\": {\"uv\": [2.75, 0.5, 3, 8.5], \"rotation\": 90, \"texture\": \"#0\"}, \"south\": {\"uv\": [2.75, 0, 5.25, 0.5], \"texture\": \"#0\"}, \"west\": {\"uv\": [0, 0.5, 0.25, 8.5], \"rotation\": 270, \"texture\": \"#0\"}, \"up\": {\"uv\": [0.25, 0.5, 2.75, 8.5], \"texture\": \"#0\"}, \"down\": {\"uv\": [3, 0.5, 5.5, 8.5], \"rotation\": 180, \"texture\": \"#0\"} } } ], \"display\": { \"thirdperson_righthand\": { \"translation\": [0, 3, 1], \"scale\": [0.55, 0.55, 0.55] }, \"thirdperson_lefthand\": { \"translation\": [0, 3, 1], \"scale\": [0.55, 0.55, 0.55] }, \"firstperson_righthand\": { \"rotation\": [-75, -173, -12], \"translation\": [0.38, 3.2, -3.5], \"scale\": [0.68, 0.68, 0.68] }, \"firstperson_lefthand\": { \"rotation\": [-75, -173, -12], \"translation\": [0.38, 3.2, -3.5], \"scale\": [0.68, 0.68, 0.68] }, \"ground\": { \"translation\": [0, 2, 0], \"scale\": [0.5, 0.5, 0.5] }, \"gui\": { \"rotation\": [30, -45, 0], \"translation\": [0, 3.5, 0], \"scale\": [0.625, 0.625, 0.625] }, \"head\": { \"rotation\": [-90, 180, 0], \"translation\": [0, 0, -14.5], \"scale\": [1.28, 1, 0.8] }, \"fixed\": { \"rotation\": [-90, 180, 0], \"translation\": [0, 0, -8.25] } } }";
        }
        return "";
    }

    public static ItemStack getCape(PlayerEntity player) {
        if (FabricLoader.getInstance().isModLoaded("trinkets")) {
            try {
                Optional<dev.emi.trinkets.api.TrinketComponent> component = dev.emi.trinkets.api.TrinketsApi.getTrinketComponent(player);

                if (component.isPresent()) {
                    dev.emi.trinkets.api.TrinketComponent trinketComponent = component.get();
                    Map<String, Map<String, dev.emi.trinkets.api.TrinketInventory>> inventory = trinketComponent.getInventory();
                    dev.emi.trinkets.api.TrinketInventory trinketInventory = inventory.get("chest").get("cape");
                    return trinketInventory.getStack(0);
                }
            } catch (Exception exception) {
                // TRINKETS IS PROBABLY NOT INSTALLED ON SERVER
            }

        }
        return player.getInventory().getArmorStack(2);
    }
}
