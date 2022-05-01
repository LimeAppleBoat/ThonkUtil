package com.jab125.thonkutil.misc.asm;

public class FabricASM implements Runnable {
    @Override
    public void run() {
        // we are very up to no good
        try {
            BoatPatch.patch();
            EnchantmentTargetPatch.patch();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
