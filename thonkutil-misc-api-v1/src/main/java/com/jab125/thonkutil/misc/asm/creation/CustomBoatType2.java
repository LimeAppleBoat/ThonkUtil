package com.jab125.thonkutil.misc.asm.creation;

import net.minecraft.block.Block;

public class CustomBoatType2 {
    //    public static CustomBoatType2 create(String internalName, Block baseBlock, String name) {
//        var types = new ArrayList<>(Arrays.asList(CustomBoatType2.field_7724));
//        var last = types.get(types.size()-1);
//        var type = new CustomBoatType2(internalName, last.ordinal() + 1, baseBlock, name);
//        types.add(type);
//        CustomBoatType2.field_7724 = types.toArray(new CustomBoatType2[0]);
//        return type;
//    }

    public static CustomBoatType2[] field_7724;
    public CustomBoatType2(String internalName, int ordinal, Block baseBlock, String name) {
        throw new IllegalStateException("ASM not applied!");
    }

    public int ordinal() {
        return 0;
    }
}
