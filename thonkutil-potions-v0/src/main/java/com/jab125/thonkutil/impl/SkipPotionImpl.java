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
        return skipList.toArray(Pair[]::new);
    }

    public static boolean contains(Potion potion, int skip) {
        for (var pair : skipList) {
            if (pair.getLeft().equals(potion)) {
                if (pair.getRight() == skip) {
                    return true;
                }
            }
        }
        return false;
    }
}
