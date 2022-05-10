package com.jab125.thonkutil.misc.api.v1;

import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;

import java.util.ArrayList;

public class ThonkUtilBoatTypes {
    public static final ArrayList<BoatTypes> boatTypes = new ArrayList<>();
    public static void createType(String fieldName, String baseBlock, String name) {
        boatTypes.add(new BoatTypes(fieldName,baseBlock,name));
    }

//    public static BoatEntity.Type getType(String fieldName) {
//        throw new AssertionError();
//    }

    public static final class BoatTypes {
        private final String fieldName;
        private final String baseBlock;
        private final String name;
        private BoatTypes(String fieldName, String baseBlock, String name) {
            this.fieldName = fieldName;
            this.baseBlock = baseBlock;
            this.name = name;
        }

        public String getBaseBlock() {
            return baseBlock;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getName() {
            return name;
        }
    }
}
