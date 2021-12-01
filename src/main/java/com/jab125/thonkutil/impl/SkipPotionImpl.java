package com.jab125.thonkutil.impl;

import net.minecraft.potion.Potion;
import net.minecraft.util.Pair;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class SkipPotionImpl {
    static ArrayList<Pair<Potion, Integer>> skipList = new ArrayList<>();
    public static void skipPotion(Potion potion, int skip) {
        skipList.add(new Pair<>(potion, skip));
    }

    @SuppressWarnings("unchecked")
    public static Pair<Potion, Integer>[] getSkipList() {
        return (Pair<Potion, Integer>[]) skipList.toArray();
    }

    public static boolean contains(Potion potion, int skip) {
        for (Pair<Potion, Integer> pair : skipList) {
            if (pair.getLeft().equals(potion)) {
                if (pair.getRight().intValue() == skip) {
                    return true;
                }
            }
        }
        return false;
    }
}
