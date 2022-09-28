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

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.concurrent.atomic.AtomicReference;

public final class MethodQueued {
    private MethodNode methodNode;
    private String classToAddTo;

    public MethodQueued(MethodNode methodNode, String classToAddTo) {
        this.methodNode = methodNode;
        this.classToAddTo = classToAddTo;
    }

    public void add(ClassNode node) {
     //   System.out.println(node.name + ", " + classToAddTo);
        if (this.methodNode != null && node.name.replaceAll("\\.", "/").equals(classToAddTo.replaceAll("\\.", "/"))) {
       //     System.out.println("Method added to class " + classToAddTo);
            AtomicReference<MethodNode> toRemove = new AtomicReference<>();
            node.methods.forEach(a -> {
                if (a.name.equals(this.methodNode.name) && a.desc.equals(this.methodNode.desc)) {
                    toRemove.set(a);
                }
            });
            if (toRemove.get() != null) {
                node.methods.remove(toRemove.get());
            }
            node.methods.add(this.methodNode);
            this.methodNode = null;
        }
    }
}
