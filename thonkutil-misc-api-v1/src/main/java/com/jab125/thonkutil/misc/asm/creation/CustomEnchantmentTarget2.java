package com.jab125.thonkutil.misc.asm.creation;

import net.minecraft.item.Item;
import java.util.function.Predicate;

//    public static CustomEnchantmentTarget2 create(String internalName, Predicate<Item> pred) {
//        var targets = new ArrayList<>(Arrays.asList(CustomEnchantmentTarget2.field_9077));
//        var last = targets.get(targets.size()-1);
//        var target = new CustomEnchantmentTarget2(internalName, last.ordinal() + 1, pred);
//        targets.add(target);
//        CustomEnchantmentTarget2.field_9077 = targets.toArray(new CustomEnchantmentTarget2[0]);
//        return target;
//    }
public enum CustomEnchantmentTarget2 {;
    public static CustomEnchantmentTarget2[] field_9077;
    private Predicate<Item> func;
//    private CustomEnchantmentTarget2(String internalName, int ordinal) {
//
//    }
//    CustomEnchantmentTarget2(String internalName, int ordinal, Predicate<Item> func) {
//        this.func = func;
//    }
//
    CustomEnchantmentTarget2(){}
    CustomEnchantmentTarget2(Predicate<Item> function) {
        this.func = function;
    }
    public boolean isAcceptableItem(Item item) {
        return this.func != null && this.func.test(item);
    }

//    public int ordinal() {
//        return 0;
//    }
}
