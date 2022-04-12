package com.jab125.thonkutil.api.events.server.entity;

import com.jab125.thonkutil.api.annotations.WillBeAvailableSoon;
import com.jab125.thonkutil.api.events.EventTaxiEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

@WillBeAvailableSoon("2.15.0")
@Deprecated
public class EntityDamagedEvent extends EventTaxiEvent {
    public EntityDamagedEvent(LivingEntity entity, DamageSource source) {
        throw new UnsupportedOperationException();
    }
}
