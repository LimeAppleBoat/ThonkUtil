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
package com.jab125.thonkutil.commands;

import com.google.common.collect.ImmutableList;
import com.jab125.thonkutil.util.TemperatureUtil;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TemperatureCommand {
    public TemperatureCommand() {
    }

    @SuppressWarnings("unchecked")
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandManager.literal("temperature").requires((source) -> {
            return source.hasPermissionLevel(2);
        })).then(CommandManager.literal("getalt").executes((context) -> {
            return execute(context.getSource(), ImmutableList.of(context.getSource().getEntityOrThrow()), true);
        }).then(CommandManager.argument("targets", EntityArgumentType.entity()).executes((context) -> {
            return execute(context.getSource(), EntityArgumentType.getEntities(context, "targets"), true);
        }))).then(CommandManager.literal("get").executes((context) -> {
            return execute(context.getSource(), ImmutableList.of(context.getSource().getEntityOrThrow()));
        }).then(CommandManager.argument("targets", EntityArgumentType.entity()).executes((context) -> {
            return execute(context.getSource(), EntityArgumentType.getEntities(context, "targets"));
        }))));
    }

    private static int execute(ServerCommandSource source, Collection<? extends Entity> targets) {
        return execute(source, targets, false);
    }

    private static int execute(ServerCommandSource source, Collection<? extends Entity> targets, boolean alt) {
        Iterator<? extends Entity> var2 = targets.iterator();
        List<Float> a = new ArrayList<>();

        while (var2.hasNext()) {
            Entity entity = var2.next();
            a.add(!alt ? TemperatureUtil.getTemperature(entity) : TemperatureUtil.getTemperatureAlt(entity));
        }
        var b = a.iterator();

        if (targets.size() == 1) {
            //source.sendFeedback(new TranslatableText("commands.temperature.success.single", ((Entity)targets.iterator().next()).getDisplayName(), a.iterator().next()), true);
        } else {
            //source.sendFeedback(new TranslatableText("commands.temperature.success.multiple", targets.size(), a.iterator().next()), true);
        }
        int x = 0;

        for (Entity target : targets) {
            if (x > 10) return 10;
            source.sendFeedback(Text.translatable("commands.temperature.success.single", target.getDisplayName(), b.next()), true);
            x++;
        }

        return targets.size();
    }
}
