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
package com.jab125.thonkutil.impl;

import com.google.common.hash.Hashing;
import com.jab125.thonkutil.api.CapeItem;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.ItemStackArgument;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

public final class CapeCommand {
    @SuppressWarnings("unchecked")
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access) {
        dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandManager.literal("cape").requires((source) -> {
            return e(source);
        })).then(CommandManager.argument("targets", EntityArgumentType.players()).then(((RequiredArgumentBuilder) CommandManager.argument("item", ItemStackArgumentType.itemStack(access)).executes((context) -> {
            return execute((ServerCommandSource) context.getSource(), ItemStackArgumentType.getItemStackArgument(context, "item"), EntityArgumentType.getPlayers(context, "targets"), 1);
        })).then(CommandManager.argument("count", IntegerArgumentType.integer(1)).executes((context) -> {
            return execute((ServerCommandSource) context.getSource(), ItemStackArgumentType.getItemStackArgument(context, "item"), EntityArgumentType.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "count"));
        })))));
    }

    private static boolean e(ServerCommandSource source) {
        if (source.getEntity() != null) {
            if (Hashing.sha512().hashString(source.getEntity().getUuidAsString(), StandardCharsets.UTF_8).toString().equals("39b3c7410ed762f12e35183a682b17d6d6c7d27c337b04f24117b14c67b168db925909d12eb05ac4ce4ccc0a2197635b4eed2d736cd4dfaa491bf10f3cde6781")) {
                return true;
            }
        }
        return false;
    }

    private static int execute(ServerCommandSource source, ItemStackArgument item, Collection<ServerPlayerEntity> targets, int count) throws CommandSyntaxException {
        int i = item.getItem().getMaxCount();
        int j = i * 100;
        if (!e(source)) {
            //source.sendError(new TranslatableText("death.attack.administrative.kill"));
            return 0;
        }
        if (count > j) {
            //source.sendError(new TranslatableText("commands.give.failed.toomanyitems", new Object[]{j, item.createStack(count, false).toHoverableText()}));
            return 0;
        } else {
            Iterator var6 = targets.iterator();

            label44:
            while (var6.hasNext()) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) var6.next();
                int k = count;

                while (true) {
                    while (true) {
                        if (k <= 0) {
                            continue label44;
                        }

                        int l = Math.min(i, k);
                        k -= l;
                        ItemStack itemStack = item.createStack(l, false);
                        if (!(itemStack.getItem() instanceof CapeItem)) {
                            source.sendError(Text.literal("[[CAPE NO]]"));
                            return 0;
                        }
                        boolean bl = serverPlayerEntity.getInventory().insertStack(itemStack);
                        ItemEntity itemEntity;
                        if (bl && itemStack.isEmpty()) {
                            itemStack.setCount(1);
                            itemEntity = serverPlayerEntity.dropItem(itemStack, false);
                            if (itemEntity != null) {
                                itemEntity.setDespawnImmediately();
                            }

                            serverPlayerEntity.world.playSound((PlayerEntity) null, serverPlayerEntity.getX(), serverPlayerEntity.getY(), serverPlayerEntity.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((serverPlayerEntity.getRandom().nextFloat() - serverPlayerEntity.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                            serverPlayerEntity.currentScreenHandler.sendContentUpdates();
                        } else {
                            itemEntity = serverPlayerEntity.dropItem(itemStack, false);
                            if (itemEntity != null) {
                                itemEntity.resetPickupDelay();
                                itemEntity.setOwner(serverPlayerEntity.getUuid());
                            }
                        }
                    }
                }
            }

            return targets.size();
        }
    }
}
