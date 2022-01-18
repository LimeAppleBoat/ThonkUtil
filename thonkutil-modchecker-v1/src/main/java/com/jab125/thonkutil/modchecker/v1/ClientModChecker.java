package com.jab125.thonkutil.modchecker.v1;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModEnvironment;
import net.minecraft.network.PacketByteBuf;

import java.util.concurrent.CompletableFuture;

import static com.jab125.thonkutil.modchecker.v1.ModChecker.CLIENT_PKT;
import static com.jab125.thonkutil.modchecker.v1.ModChecker.UNIVERSAL_PKT;

public class ClientModChecker implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientLoginNetworking.registerGlobalReceiver(UNIVERSAL_PKT, (client, handler, bf, listenerAdder) -> {
            String b = "";
            for (var container : FabricLoader.getInstance().getAllMods()) {
                if (!container.getMetadata().getEnvironment().equals(ModEnvironment.UNIVERSAL)) continue;
                b = b + container.getMetadata().getId() + "~";
            }
            b = removeLastChar(b) + "|";
            for (var container : FabricLoader.getInstance().getAllMods()) {
                if (!container.getMetadata().getEnvironment().equals(ModEnvironment.CLIENT)) continue;
                b = b + container.getMetadata().getId() + "~";
            }
            b = removeLastChar(b);
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeString(b);
            return CompletableFuture.completedFuture(buf);
        });
    }
    public static String removeLastChar(String str) {
        return removeLastChars(str, 1);
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }
}
