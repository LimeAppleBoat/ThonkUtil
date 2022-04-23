package com.jab125.thonkutil.misc.impl.v1;

public interface ExtendableEnum {
    /**
     * Called by generated factory code to do any post-constructor setup required by
     * the enum. Should not be called manually.
     */
    @Deprecated
    default void init() {}
}
