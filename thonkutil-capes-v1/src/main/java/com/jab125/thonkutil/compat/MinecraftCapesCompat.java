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
