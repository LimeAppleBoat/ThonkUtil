package com.jab125.thonkutil.impl;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class RemovePotionRecipeImpl {

    public static class RemoveTippedArrowRecipeImpl {

        static ArrayList<Potion> tipped_arrow_potions = new ArrayList<>();

        public static void removeTippedArrowRecipe(Potion potion) {
            tipped_arrow_potions.add(potion);
        }

        public static Potion[] getTipped_arrow_potions() {
            return tipped_arrow_potions.toArray(Potion[]::new);
        }

        public static boolean contains(Potion potion) {
            //System.out.println(Registry.POTION.getId(potion).toString());
            for (var potion2 : tipped_arrow_potions) {
                //System.out.println(Registry.POTION.getId(potion2).toString());
                if (potion2.equals(potion)) return true;
            }
            return false;
        }
    }

    public static class RemoveSplashPotionRecipeImpl {

        static ArrayList<Potion> splash_potions = new ArrayList<>();

        public static void removeSplashPotionRecipe(Potion potion) {
            splash_potions.add(potion);
        }

        public static Potion[] getSplashPotions() {
            return splash_potions.toArray(Potion[]::new);
        }

        public static boolean contains(Potion potion) {
            //System.out.println(Registry.POTION.getId(potion).toString());
            for (var potion2 : splash_potions) {
                //System.out.println(Registry.POTION.getId(potion2).toString());
                if (potion2.equals(potion)) return true;
            }
            return false;
        }
    }

    public static class RemoveLingeringPotionRecipeImpl {

        static ArrayList<Potion> lingering_potions = new ArrayList<>();

        public static void removeLingeringPotionRecipe(Potion potion) {
            lingering_potions.add(potion);
        }

        public static Potion[] getLingeringPotions() {
            return lingering_potions.toArray(Potion[]::new);
        }

        public static boolean contains(Potion potion) {
            //System.out.println(Registry.POTION.getId(potion).toString());
            for (var potion2 : lingering_potions) {
                //System.out.println(Registry.POTION.getId(potion2).toString());
                if (potion2.equals(potion)) return true;
            }
            return false;
        }
    }

    @Deprecated
    public static class RemovePotionRecipeImpl2 {


        static ArrayList<Potion> potions = new ArrayList<>();

        public static void removePotionRecipe(Potion potion) {
            potions.add(potion);
        }

        public static Potion[] getPotions() {
            return potions.toArray(Potion[]::new);
        }

        public static boolean contains(Potion potion) {
            //System.out.println(Registry.POTION.getId(potion).toString());
            for (var potion2 : potions) {
                //System.out.println(Registry.POTION.getId(potion2).toString());
                if (potion2.equals(potion)) return true;
            }
            return false;
        }
    }

    public static boolean shouldCraft(ItemStack potionStack, ItemStack itemStack) {
        if (itemStack.getItem().equals(Items.GUNPOWDER) && potionStack.getItem().equals(Items.POTION) && RemoveSplashPotionRecipeImpl.contains(PotionUtil.getPotion(potionStack)))
            return false;
        if (itemStack.getItem().equals(Items.DRAGON_BREATH) && potionStack.getItem().equals(Items.SPLASH_POTION) && RemoveLingeringPotionRecipeImpl.contains(PotionUtil.getPotion(potionStack)))
            return false;
        return true;
    }
}
