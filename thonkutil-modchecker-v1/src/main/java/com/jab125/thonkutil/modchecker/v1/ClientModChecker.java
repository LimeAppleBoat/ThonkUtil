package com.jab125.thonkutil.modchecker.v1;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModEnvironment;
import net.minecraft.network.PacketByteBuf;

import java.util.concurrent.CompletableFuture;

import static com.jab125.thonkutil.modchecker.v1.ModChecker.UNIVERSAL_PKT;

public class ClientModChecker implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        new C().l();
        if (!(new C().a == 2)) {
            System.exit(0);
        }
    }

    private class C {
        private static int a = 0;

        C() {
        }

        C l() {
            a++;
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
            a++;
            return new C();
        }
    }

    public static String removeLastChar(String str) {
        return removeLastChars(str, 1);
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }
}
