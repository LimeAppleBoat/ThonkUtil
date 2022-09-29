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
package com.jab125.limeappleboat.thonkutil.enumapi.testmod;

import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.EnumAdder;
import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.MethodName;

import java.util.List;

public class EnumTest implements Runnable {
    @Override
    public void run() {
        new EnumAdder(
            "com.jab125.limeappleboat.thonkutil.enumapi.testmod.TestEnum",
            "$VALUES",
            "com.jab125.limeappleboat.thonkutil.enumapi.testmod.TestEnumCreator",
            "(Ljava/lang/String;I)V"
        ).register();
        new EnumAdder(
            "com.jab125.limeappleboat.thonkutil.enumapi.testmod.TestAbstractEnum",
            "$VALUES",
            "com.jab125.limeappleboat.thonkutil.enumapi.testmod.TestAbstractEnumCreator",
            "(Ljava/lang/String;I)V",
            List.of(
                new MethodName(
                    "yell",
                    "()V",
                    "extension$yell"
                ),
                new MethodName(
                    "scream",
                    "()V",
                    "extension$scream")
                )
        ).register();
    }
}
