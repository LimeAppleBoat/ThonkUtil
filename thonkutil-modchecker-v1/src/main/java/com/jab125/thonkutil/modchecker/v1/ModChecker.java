/*
 * Copyright (c) 2021, 2022 Jab125 & LimeAppleBoat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jab125.thonkutil.modchecker.v1;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ModChecker implements ModInitializer {
    protected static final Identifier UNIVERSAL_PKT = new Identifier("thonkutil", "universal_packet");

    @Override
    public void onInitialize() {
        ServerLoginConnectionEvents.QUERY_START.register(this::onLoginStart);

        ServerLoginNetworking.registerGlobalReceiver(UNIVERSAL_PKT, (server, handler, understood, buf, synchronizer, sender) -> {
            if (understood) {
                System.out.println("Understood response from client in " + UNIVERSAL_PKT);
                String str = buf.readString();
                String[] universalModList = str.split("\\|")[0].split("~");
                String[] clientModList = str.split("\\|")[1].split("~");
                List<String> collection = new ArrayList<>();
                List<String> client_collection = new ArrayList<>();
                for (String mod : universalModList) {
                    if (getBlackListedMods().contains(mod) && !collection.contains(mod)) {
                        collection.add(mod);
                    }
                    //System.out.println(mod);
                }

                for (String mod : clientModList) {
                    if (getBlackListedMods().contains(mod) && !collection.contains(mod)) {
                        client_collection.add(mod);
                    }
                    //System.out.println(mod);
                }
                if (!collection.isEmpty() || !client_collection.isEmpty()) {
                    String pth = "";
                    for (int i = 0; i < collection.toArray().length; i++) {
                        if (i == collection.toArray().length - 1) {
                            pth = pth + collection.toArray()[i];
                        } else if (i == collection.toArray().length - 2) {
                            pth = pth + collection.toArray()[i] + "\n";
                        } else {
                            pth = pth + collection.toArray()[i] + "\n";
                        }
                    }

                    String pt = "";
                    for (int i = 0; i < client_collection.toArray().length; i++) {
                        if (i == client_collection.toArray().length - 1) {
                            pt = pt + client_collection.toArray()[i];
                        } else if (i == collection.toArray().length - 2) {
                            pt = pt + client_collection.toArray()[i] + "\n";
                        } else {
                            pt = pt + client_collection.toArray()[i] + "\n";
                        }
                    }

                    Text kickMsg = Text.literal("The following mods are blacklisted:\n\n")
                            .append(Text.literal("Universal Mods:\n").formatted(Formatting.BOLD)).append(pth + "\n\n")
                            .append(Text.literal("Client Side Mods:\n").formatted(Formatting.BOLD)).append(pt + "\n\n")
                            .append("Please uninstall all of these mods to join.");
                    handler.disconnect(kickMsg);
                }
            } else {
                System.err.println("Client did not understand response query message with channel name " + UNIVERSAL_PKT);
                handler.disconnect(Text.literal(""));
            }
        });

        if (CheckerUtil.isClient()) {
            // Client-side here
        } else {
            // Server-side here

        }
    }

    private void onLoginStart(ServerLoginNetworkHandler networkHandler, MinecraftServer server, PacketSender sender, ServerLoginNetworking.LoginSynchronizer synchronizer) {
        // Send a dummy query when the client starts accepting queries.
        sender.sendPacket(ModChecker.UNIVERSAL_PKT, PacketByteBufs.empty()); // dummy packet
    }

    public static Set<String> getBlackListedMods() {
        return Collections.emptySet();
    }
}
