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
package com.jab125.limeappleboat.thonkutil.enumapi.v1.impl;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public enum MixinDefiner {;
    public static List<Object> defineMixin(String clazz) {
        var name = "com/jab125/limeappleboat/thonkutil/enumapi/v1/impl/mixin/generatedMixins/" + UUID.randomUUID().toString().replaceAll("-", "");
        var classWriter = new ClassWriter(0);
        classWriter.visit(52, Opcodes.ACC_PUBLIC | Opcodes.ACC_ABSTRACT, name, null, "java/lang/Object", null);
        createMixinAnnotation(classWriter, clazz);
        classWriter.visitEnd();
        try {
            Files.write(Paths.get(name.replaceAll("com/jab125/limeappleboat/thonkutil/enumapi/v1/impl/mixin/generatedMixins/", "") + ".class"), classWriter.toByteArray());
        } catch (Exception e){};
        return List.of(name, classWriter.toByteArray());
//        classWriter.visitAnnotation("L/org/spongepowered/asm/mixin/Mixin;", false);
//        var node = new ClassNode();
//        node.invisibleAnnotations.add(createMixinAnnotation(clazz));
       // node.visibleAnnotations.add(new AnnotationNode())
    }

    private static void createMixinAnnotation(ClassWriter classWriter, String clazz) {
        var annotation = classWriter.visitAnnotation("Lorg/spongepowered/asm/mixin/Mixin;", false);
        var value = annotation.visitArray("value");
        value.visit(null, Type.getType("L" + clazz.replaceAll("\\.", "/") + ";"));
        value.visitEnd();
        annotation.visitEnd();
//      //  @Lorg/spongepowered/asm/mixin/Mixin;(value={net.minecraft.client.render.entity.LivingEntityRenderer.class})
//        var node = new AnnotationNode("L/org/spongepowered/asm/mixin/Mixin;");
//        node.values.add("value");
//        node.values.add(new String[]{clazz.replaceAll("/", ".")});
//        return node;
    }
}
