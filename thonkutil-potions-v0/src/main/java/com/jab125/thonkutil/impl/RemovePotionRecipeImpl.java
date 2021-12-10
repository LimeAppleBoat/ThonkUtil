package com.jab125.thonkutil.impl;

import net.minecraft.potion.Potion;
import org.apache.commons.lang3.NotImplementedException;

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
            if (true) throw new NotImplementedException("NOT IMPLEMENTED");
            splash_potions.add(potion);
        }

        public static Potion[] getSplashPotions() {
            if (true) throw new NotImplementedException("NOT IMPLEMENTED");
            return splash_potions.toArray(Potion[]::new);
        }

        public static boolean contains(Potion potion) {
            if (true) throw new NotImplementedException("NOT IMPLEMENTED");
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
            if (true) throw new NotImplementedException("NOT IMPLEMENTED");
            lingering_potions.add(potion);
        }

        public static Potion[] getLingeringPotions() {
            if (true) throw new NotImplementedException("NOT IMPLEMENTED");
            return lingering_potions.toArray(Potion[]::new);
        }

        public static boolean contains(Potion potion) {
            if (true) throw new NotImplementedException("NOT IMPLEMENTED");
            //System.out.println(Registry.POTION.getId(potion).toString());
            for (var potion2 : lingering_potions) {
                //System.out.println(Registry.POTION.getId(potion2).toString());
                if (potion2.equals(potion)) return true;
            }
            return false;
        }
    }

    public static class RemovePotionRecipeImpl2 {


        static ArrayList<Potion> potions = new ArrayList<>();

        public static void removePotionRecipe(Potion potion) {
            if (true) throw new NotImplementedException("NOT IMPLEMENTED");
            potions.add(potion);
        }

        public static Potion[] getPotions() {
            if (true) throw new NotImplementedException("NOT IMPLEMENTED");
            return potions.toArray(Potion[]::new);
        }

        public static boolean contains(Potion potion) {
            if (true) throw new NotImplementedException("NOT IMPLEMENTED");
            //System.out.println(Registry.POTION.getId(potion).toString());
            for (var potion2 : potions) {
                //System.out.println(Registry.POTION.getId(potion2).toString());
                if (potion2.equals(potion)) return true;
            }
            return false;
        }
    }
}
