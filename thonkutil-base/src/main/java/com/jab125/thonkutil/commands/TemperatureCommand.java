//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jab125.thonkutil.commands;

import com.google.common.collect.ImmutableList;
import com.jab125.thonkutil.util.TemperatureUtil;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;

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
            source.sendFeedback(new TranslatableText("commands.temperature.success.single", target.getDisplayName(), b.next()), true);
            x++;
        }

        return targets.size();
    }
}
