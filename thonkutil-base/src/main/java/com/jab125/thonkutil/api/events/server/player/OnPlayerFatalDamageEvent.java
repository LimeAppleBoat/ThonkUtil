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
package com.jab125.thonkutil.api.events.server.player;

import com.jab125.thonkutil.api.events.EventTaxiBooleanReturnableEvent;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnPlayerFatalDamageEvent extends EventTaxiBooleanReturnableEvent {
    private final ServerPlayerEntity player;
    private final DamageSource damageSource;
    private boolean allowDeath = true;
    private final float damageAmount;

    public OnPlayerFatalDamageEvent(ServerPlayerEntity player, DamageSource damageSource, float damageAmount) {
        this.damageAmount = damageAmount;
        this.player = player;
        this.damageSource = damageSource;
    }

    // please also set the player's health to >0, because for some reason, the player will still die if you don't do that
    public void shouldAllowDeath(boolean bool) {
        if (!bool) allowDeath = false;
    }

    public ServerPlayerEntity getPlayer() {
        return player;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }

    public float getDamageAmount() {
        return damageAmount;
    }

    /**
     * internal method
     */
    @Override
    @Deprecated
    public boolean getBoolean() {
        return allowDeath;
    }
}
