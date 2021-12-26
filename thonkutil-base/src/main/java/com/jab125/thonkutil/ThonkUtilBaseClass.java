package com.jab125.thonkutil;

public interface ThonkUtilBaseClass extends ThonkUtilClass {
    @Override
    default String modId() {
        return "thonkutil-base";
    };
}
