package com.jab125.thonkutil.commands;

import com.google.common.hash.Hashing;
//import com.jab125.thonkutil.api.CapeItem;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class SetCosmeticCommand {
    @SuppressWarnings("unchecked")
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("thonkutil:cosmetic").requires((source) -> {
            return e(source);
        }).executes(context->execute(context.getSource(), "", false))
                .then(CommandManager.argument("cosmetic", StringArgumentType.string()).executes((context) -> {
            return execute(context.getSource(), StringArgumentType.getString(context, "cosmetic"), false);
                }
        ).then(CommandManager.argument("enchanted", BoolArgumentType.bool()).executes((context)-> {
            return execute(context.getSource(), StringArgumentType.getString(context, "cosmetic"), BoolArgumentType.getBool(context, "enchanted"));
                }))));
    }

    private static boolean e(ServerCommandSource source) {
        if (source.getEntity() == null) return false;
        return source.getEntity() instanceof PlayerEntity;
    }

    private static int execute(ServerCommandSource source, String cosmetic, boolean enchanted) throws CommandSyntaxException {
        Objects.requireNonNull(source.getEntity());
        PlayerEntity player = source.getPlayer();
        if (player.thonkutil$ownsCosmetic(cosmetic)) {
            player.thonkutil$setCosmetic(cosmetic);
            source.sendFeedback(new LiteralText("Cosmetic changed."), false);
            if (!player.thonkutil$cosmeticEnchantable(cosmetic) && enchanted) source.sendError(new LiteralText("Enchantment glint cannot be applied as you have not unlocked enchantments for this cosmetic."));
            else {
                player.thonkutil$setEnchanted(enchanted);
                source.sendFeedback(new LiteralText("Cosmetic enchantment glint " + (enchanted ? "enabled." : "disabled.")), false);
            }
        }
        else source.sendError(new LiteralText("Cosmetic change failed as you do not own the cosmetic."));
        return 1;
    }
}
