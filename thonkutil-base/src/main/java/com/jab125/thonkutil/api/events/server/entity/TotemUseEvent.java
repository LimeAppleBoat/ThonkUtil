package com.jab125.thonkutil.api.events.server.entity;

import com.jab125.thonkutil.ThonkUtil;
import com.jab125.thonkutil.api.events.EventTaxiBooleanReturnableEvent;
import com.jab125.thonkutil.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;

import static com.jab125.thonkutil.util.Util.isModInstalled;

public class TotemUseEvent extends EventTaxiBooleanReturnableEvent {
    private final LivingEntity entity;
    private final DamageSource source;
    private boolean saveEntity = false;
    private ItemStack item = ItemStack.EMPTY;

    public TotemUseEvent(LivingEntity entity, DamageSource source) {
        this.entity = entity;
        this.source = source;
    }

    @Environment(EnvType.CLIENT)
    public static void playActivateAnimation(Entity entity, ItemStack item) {
        if (item.equals(ItemStack.EMPTY)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.particleManager.addEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30); // particles
        assert mc.world != null;
        mc.world.playSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_TOTEM_USE,
                entity.getSoundCategory(), 1.0F, 1.0F, false); // sound
        if (entity == mc.player) {
            mc.gameRenderer.showFloatingItem(item); // animation
        }
    }

    public void setTotemActivateItem(ItemStack item) {
        this.item = item;
    }

    public void regenerateHealth() {
        entity.setHealth(1.0F);
    }

    public ItemStack findTotem(Item item) {
        ItemStack itemStack = null;
        Hand[] var4 = Hand.values();

        if (this.getEntity() instanceof ServerPlayerEntity entity && isModInstalled("trinkets"))
            itemStack = Util.findTrinketsItem(item, entity);

        if (itemStack == null || itemStack.equals(ItemStack.EMPTY))
            for (Hand hand : var4) {
                ItemStack itemStack2 = entity.getStackInHand(hand);
                if (itemStack2.isOf(item)) {
                    itemStack = itemStack2;
                    break;
                }
            }
        return itemStack == null ? ItemStack.EMPTY : itemStack;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public DamageSource getSource() {
        return source;
    }

    public boolean isOutOfWorld() {
        return source.isOutOfWorld();
    }

    @Override
    public boolean getBoolean() {
        return saveEntity;
    }

    public void saveEntity() {
        saveEntity(true);
    }

    public void saveEntity(boolean regenerateHealth) {
        this.saveEntity = true;
        if (regenerateHealth) regenerateHealth();
        cancel();
    }

    public void playActivateAnimation() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeItemStack(item);
        buf.writeInt(entity.getId());
        if (entity instanceof ServerPlayerEntity player) {
            ServerPlayNetworking.send(player, ThonkUtil.TOTEM_PACKET, buf);
        }
        for (ServerPlayerEntity player2 : PlayerLookup.tracking((ServerWorld) entity.world,
                entity.getBlockPos())) {
            ServerPlayNetworking.send(player2, ThonkUtil.TOTEM_PACKET, buf);
        }
    }

    public void genericTotemExecution(Item item) {
        ItemStack itemStack = findTotem(item);
        if (itemStack.equals(ItemStack.EMPTY)) return;
        itemStack.decrement(1);
        saveEntity();
        incrementStat(item);
        setTotemActivateItem(itemStack);
        playActivateAnimation();
        entity.setHealth(1.0F);
        entity.clearStatusEffects();
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
    }

    public void incrementStat(Item item) {
        if (this.item.equals(ItemStack.EMPTY)) return;
        if (entity instanceof ServerPlayerEntity serverPlayerEntity)
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(item));
    }
}
