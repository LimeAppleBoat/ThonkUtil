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
package com.jab125.limeappleboat.thonkutil.enumapi.v1.api;

import java.util.ArrayList;
import java.util.List;

public record EnumAdder(String enumClass, String valuesField, String surrogateClass, String desc, List<MethodName> names) {
    public EnumAdder(String enumClass, String valuesField, String surrogateClass, String desc) {
        this(enumClass, valuesField, surrogateClass, desc, null);
    }
    @SuppressWarnings("unused")
    private static final List<EnumAdder> additions = new ArrayList<>();
    public void register() {
        additions.add(this);
    }
}
