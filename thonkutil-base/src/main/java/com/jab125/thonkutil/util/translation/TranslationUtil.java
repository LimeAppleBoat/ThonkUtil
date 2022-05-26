/*
 * Copyright (c) 2021, 2022 Jab125 & LimeAppleBoat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jab125.thonkutil.util.translation;

import com.jab125.thonkutil.ThonkUtilBaseClass;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import java.text.NumberFormat;
import java.util.Arrays;

public class TranslationUtil implements ThonkUtilBaseClass {
    private TranslationUtil() {
        throw new RuntimeException("This Util class is not allowed to be instantiated!");
    }

    public static Text translateNumeric(String key, int[]... args) {
        Object[] realArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            NumberFormat nf = NumberFormat.getInstance();
            if (args[i].length == 1) {
                realArgs[i] = nf.format(args[i][0]);
            } else {
                assert args[i].length == 2;
                realArgs[i] = nf.format(args[i][0]) + "/" + nf.format(args[i][1]);
            }
        }

        int[] override = new int[args.length];
        Arrays.fill(override, -1);
        for (int i = 0; i < args.length; i++) {
            int[] arg = args[i];
            if (arg == null) {
                throw new NullPointerException("args[" + i + "]");
            }
            if (arg.length == 1) {
                override[i] = arg[0];
            }
        }

        String lastKey = key;
        for (int flags = (1 << args.length) - 1; flags >= 0; flags--) {
            StringBuilder fullKey = new StringBuilder(key);
            for (int i = 0; i < args.length; i++) {
                fullKey.append('.');
                if (((flags & (1 << i)) != 0) && override[i] != -1) {
                    fullKey.append(override[i]);
                } else {
                    fullKey.append('n');
                }
            }
            lastKey = fullKey.toString();
            if (I18n.hasTranslation(lastKey)) {
                return Text.translatable(lastKey, realArgs);
            }
        }
        return Text.translatable(lastKey, realArgs);
    }

    public static String translationKeyOf(String type, String id, String modid) {
        return type + "." + modid + "." + id;
    }
}