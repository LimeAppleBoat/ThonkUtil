package com.jab125.thonkutil.additionalcapes.v1.datagen;

import com.jab125.thonkutil.additionalcapes.v1.item.AdditionalCapeItems;
import com.jab125.thonkutil.api.CapeItem;
import com.jab125.thonkutil.api.datagen.ThonkUtilLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.apache.commons.lang3.text.WordUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class AdditionalCapesDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(new LanguageGen(fabricDataGenerator, "en_us"));
    }

    private static class LanguageGen extends ThonkUtilLanguageProvider {

        public LanguageGen(FabricDataGenerator fabricDataGenerator, String locale) {
            super(fabricDataGenerator, locale);
        }

        @Override
        protected void addTranslations() {
            for (Field cape : AdditionalCapeItems.class.getFields()) {
                if (Modifier.isStatic(cape.getModifiers()) && cape.getType().isAssignableFrom(CapeItem.class)) {
                    try {
                        add(((CapeItem) cape.get(null)), WordUtils.capitalizeFully(cape.getName().toLowerCase().replaceAll("_", " ")));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
