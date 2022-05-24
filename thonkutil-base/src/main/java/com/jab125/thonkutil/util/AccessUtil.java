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
package com.jab125.thonkutil.util;

import java.util.Objects;

public class AccessUtil {
    private AccessUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * if you can't use an accessor (because you want to be mixin-free} or an access widener (for some reason), use this.
     *
     * @param classPath  the path to the class, for example {@link AccessUtil this} class would be {@code com.jab125.thonkutil.util.AccessUtil}
     * @param methodName the name of the method, for this method it is {@code callMethod}
     * @param params     the parameters of the method, example: {@code new Object[]{"com.jab125.thonkutil.util.AccessUtil", "callMethod", etc..}}
     * @return the result, can be {@code null} if its return type is {@code void}
     */
    public static Object callStaticMethod(String classPath, String methodName, Class<?>[] paramTypes, Object[] params) {
        try {
            Class.forName(classPath).getDeclaredMethod(methodName, paramTypes).setAccessible(true);
            return Class.forName(classPath).getDeclaredMethod(methodName, paramTypes).invoke(null, params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object callMethod(Object callFrom, String classPath, String methodName, Class<?>[] paramTypes, Object[] params) {
        try {
            Class.forName(classPath).getDeclaredMethod(methodName, paramTypes).setAccessible(true);
            return Class.forName(classPath).getDeclaredMethod(methodName, paramTypes).invoke(callFrom, params);
        } catch (Exception e) {
            return null;
        }
    }

    private static void testStaticMethod(String hoI) {
        System.out.println(hoI);
    }

    public static final class TestClass {
        private final String cake;
        private final String onions;

        public TestClass(String cake, String onions) {
            this.cake = cake;
            this.onions = onions;
        }

        private void testMethod(String hoI) {
            System.out.println(hoI);
            System.out.println(this.toString());
        }

        public String cake() {
            return cake;
        }

        public String onions() {
            return onions;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (TestClass) obj;
            return Objects.equals(this.cake, that.cake) &&
                    Objects.equals(this.onions, that.onions);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cake, onions);
        }

        @Override
        public String toString() {
            return "TestClass[" +
                    "cake=" + cake + ", " +
                    "onions=" + onions + ']';
        }


    }
}
