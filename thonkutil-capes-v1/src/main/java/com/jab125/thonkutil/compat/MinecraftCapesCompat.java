package com.jab125.thonkutil.compat;

import com.jab125.thonkutil.ThonkUtilCapes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftcapes.compatibility.CompatHooks;
import net.minecraftcapes.compatibility.ICompatHooks;
import net.minecraftcapes.player.PlayerHandler;

public class MinecraftCapesCompat implements ICompatHooks {
    public MinecraftCapesCompat() {
        CompatHooks.addHook(this);
    }

    @Override
    public void onPlayerRender(PlayerEntity playerEntity) {
        PlayerHandler playerHandler = PlayerHandler.getFromPlayer(playerEntity);
        if (!ThonkUtilCapes.getCape(playerEntity).isEmpty()) {
            playerHandler.setForceHideElytra(true);
            playerHandler.setShowCape(false);
        } else {
            playerHandler.setForceHideElytra(false);
            playerHandler.setShowCape(true);
        }
    }
}
