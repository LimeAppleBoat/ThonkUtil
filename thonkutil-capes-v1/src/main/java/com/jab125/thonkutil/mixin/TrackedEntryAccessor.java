package com.jab125.thonkutil.mixin;

import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Tag.TrackedEntry.class)
public interface TrackedEntryAccessor {
    @Invoker("<init>")
    static Tag.TrackedEntry createTrackedEntry(Tag.Entry entry, String source) {
        throw new UnsupportedOperationException();
    }
}
