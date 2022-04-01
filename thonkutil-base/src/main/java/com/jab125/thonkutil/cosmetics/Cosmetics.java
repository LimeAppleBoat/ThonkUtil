package com.jab125.thonkutil.cosmetics;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.jab125.thonkutil.cosmetics.exclamationmark.ExclamationMarkModel;
import com.jab125.thonkutil.cosmetics.spinningpenguins.SpinningPenguinsModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;
import net.minecraft.util.Util;
import org.apache.commons.io.IOUtils;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cosmetics {
    public static final ArrayList<CosmeticType> cosmetics = new ArrayList<>();
    @Environment(EnvType.CLIENT)
    public static final EntityModelLayer EXCLAMATION_MARK = new EntityModelLayer(cosmeticId("exclamation_mark"), "exclamation_mark");
    @Environment(EnvType.CLIENT)
    public static final EntityModelLayer SPINNING_PENGUINS = new EntityModelLayer(cosmeticId("spinning_penguins"), "spinning_penguins");
    @Environment(EnvType.CLIENT)
    public static void init() {
        EntityModelLayerRegistry.registerModelLayer(EXCLAMATION_MARK, ExclamationMarkModel::createTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SPINNING_PENGUINS, SpinningPenguinsModel::createTexturedModelData);
    }

    private static Identifier cosmeticId(String id) {
        return new Identifier("thonkutil", "cosmetic_" + id);
    }

    public static void load() {
        Util.getMainWorkerExecutor().execute(() -> {
            long load = System.currentTimeMillis();
            System.out.println("ThonkUtil: loading cosmetics...");
            try {
                var connection = new URL("https://raw.githubusercontent.com/LimeAppleBoat/.github/main/thonkutil/cosmetics.json").openConnection();
                connection.setUseCaches(false);
                String json = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
                JsonArray jsonArray = JsonHelper.deserializeArray(new StringReader(json));
                for (JsonElement element : jsonArray) {
                    if (element.isJsonObject() && element instanceof JsonObject jsonObject) {
                        if (!jsonObject.has("uuid") || !jsonObject.has("cosmetics")) continue;
                        String uuid = jsonObject.get("uuid").getAsString();
                        String name = jsonObject.get("name").getAsString();
                        boolean enchantable = jsonObject.has("enchantable") && jsonObject.get("enchantable").getAsBoolean();
                        JsonArray cosmetics = jsonObject.getAsJsonArray("cosmetics");
                        ArrayList<Pair<String, Boolean>> cosmeticList = new ArrayList<>();
                        for (JsonElement cosmetic : cosmetics) {
                            if (cosmetic.isJsonObject()) {
                                if (!cosmetic.getAsJsonObject().has("id")) continue;
                                String cos = cosmetic.getAsJsonObject().get("id").getAsString();
                                boolean enchant = cosmetic.getAsJsonObject().has("enchantable") ? cosmetic.getAsJsonObject().get("enchantable").getAsBoolean() : enchantable;
                                cosmeticList.add(new Pair<>(cos, enchant));
                            } else {
                                cosmeticList.add(new Pair<>(cosmetic.getAsString(), enchantable));
                            }
                        }
                        Cosmetics.cosmetics.add(new CosmeticType(uuid, name, enchantable, cosmeticList));
                    } // quietly ignore errors
                }
                System.out.println("ThonkUtil: Loaded Cosmetics, took " + (System.currentTimeMillis() - load) + " ms.");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.exit(-234);
        });
    }

    public static final record CosmeticType(String uuid, String name, boolean enchantable, ArrayList<Pair<String, Boolean>> cosmetics) {
        @Override
        public String toString() {
            ArrayList<String> visualCosmetics = new ArrayList<>();
            for (Pair<String, Boolean> cosmetic : cosmetics) {
                String str = "['" + cosmetic.getLeft() + "', " + cosmetic.getRight() + "]";
                visualCosmetics.add(str);
            }
            return "CosmeticType{" +
                    "uuid='" + uuid + '\'' +
                    ", name='" + name + '\'' +
                    ", enchantable=" + enchantable +
                    ", cosmetics=" + visualCosmetics +
                    '}';
        }
    }
}
