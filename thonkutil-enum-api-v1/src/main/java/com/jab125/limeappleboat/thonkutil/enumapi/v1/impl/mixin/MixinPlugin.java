//        System.out.println(methodQueues);
//        methodQueues.forEach(methodQueued -> methodQueued.add(targetClass));
//        //System.out.println(targetClassName);
//        if (targetClassName.equals("com.jab125.limeappleboat.thonkutil.enumapi.v1.api.BoatTypeAdder")) {
//        for (MethodNode method : targetClass.methods) {
//        if (method.name.equals("create") || method.name.equals("addToField")) {
//        System.out.println(method.name);
//        for (AbstractInsnNode instruction : method.instructions) {
//        System.out.println(instruction.getClass());
//        if (instruction instanceof LabelNode labelNode) {
//        System.out.println(labelNode.getLabel());
//        } else if (instruction instanceof LineNumberNode lineNumberNode) {
//        System.out.println(lineNumberNode.start.getLabel() + ", " + lineNumberNode.line);
//        } else if (instruction instanceof TypeInsnNode typeInsnNode) {
//        System.out.println(typeInsnNode.desc + ", " + typeInsnNode.invisibleTypeAnnotations + ", " + typeInsnNode.visibleTypeAnnotations + ", " + typeInsnNode.getType() + ", " + typeInsnNode.getOpcode());
//        } else if (instruction instanceof MethodInsnNode methodInsnNode) {
//        System.out.println(methodInsnNode.getOpcode() + ", " + methodInsnNode.desc + ", " + methodInsnNode.name + ", " + methodInsnNode.owner + ", " + methodInsnNode.invisibleTypeAnnotations + ", " + methodInsnNode.visibleTypeAnnotations + ", " + methodInsnNode.itf);
//        } else if (instruction instanceof InsnNode insnNode) {
//        System.out.println(insnNode.getOpcode() + ", " + insnNode.invisibleTypeAnnotations + ", " + insnNode.visibleTypeAnnotations + ", " + insnNode.getType());
//        } else if (instruction instanceof VarInsnNode varInsnNode) {
//        System.out.println(varInsnNode.var + ", " + varInsnNode.getType() + ", " + varInsnNode.getOpcode() + ", " + varInsnNode.invisibleTypeAnnotations + ", " + varInsnNode.visibleTypeAnnotations);
//        } else if (instruction instanceof FieldInsnNode fieldInsnNode) {
//        System.out.println(fieldInsnNode.desc + ", " + fieldInsnNode.name + ", " + fieldInsnNode.owner + ", " + fieldInsnNode.invisibleTypeAnnotations + ", " + fieldInsnNode.visibleTypeAnnotations + ", " + fieldInsnNode.getType() + ", " + fieldInsnNode.getOpcode());
//        }
//        }
//        }
//        }
//        }
//        //if(true)return;
package com.jab125.limeappleboat.thonkutil.enumapi.v1.impl.mixin;

import com.google.common.collect.ImmutableList;
import com.jab125.limeappleboat.thonkutil.enumapi.v1.impl.MethodName;
import com.jab125.limeappleboat.thonkutil.enumapi.v1.impl.MethodQueued;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.gui.FabricStatusTree;
import net.fabricmc.loader.impl.launch.FabricLauncherBase;
import net.fabricmc.loader.impl.util.mappings.TinyRemapperMappingsHelper;
import net.fabricmc.tinyremapper.IMappingProvider;
import net.fabricmc.tinyremapper.TinyRemapper;
import net.fabricmc.tinyremapper.TinyUtils;
import net.minecraft.enchantment.EnchantmentTarget;
import org.apache.commons.lang3.text.WordUtils;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MixinPlugin implements IMixinConfigPlugin {
    private final ArrayList<MethodQueued> methodQueues = new ArrayList<>();
    private static final String PREFIX = "^_^ + 08 - e rT 7 ( 馬鹿_イーヌン_";
    private final IMappingProvider what = TinyRemapperMappingsHelper.create(FabricLauncherBase.getLauncher().getMappingConfiguration().getMappings(), FabricLoader.getInstance().getMappingResolver().getCurrentRuntimeNamespace(), "intermediary");
    private String remapDescriptor(String desc) {
        var a = Arrays.stream(Type.getArgumentTypes(desc)).toList();
        var b = new ArrayList<Type>();
        for (Type type : a) {
            var c = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", type.getClassName());
            b.add(Type.getObjectType(c.replaceAll("\\.", "/")));
        }
        var i = 0;
        for (Type type : a) {
            desc = desc.replace(type.getInternalName(), b.get(i).getInternalName());
            i++;
        }
        return desc;
    }
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        System.out.println(myTargets);
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
//        System.out.println(targetClassName);
//        for (MethodNode method : targetClass.methods) {
//            System.out.println(method);
//        }
    }
    private static void createAddToFieldConstructor(InsnList insnList, String clazz, String field) {
        clazz = clazz.replaceAll("\\.", "/");
        var label0 = new Label();
        var label1 = new Label();
        var label2 = new Label();
        var label3 = new Label();
        var label4 = new Label();
        var node1 = new LabelNode(label0);
        var node2 = new TypeInsnNode(Opcodes.NEW, "java/util/ArrayList");
        var node3 = new InsnNode(Opcodes.DUP);
        var node4 = new FieldInsnNode(Opcodes.GETSTATIC, clazz, field, "[L" + clazz + ";");
        var node5 = new MethodInsnNode(Opcodes.INVOKESTATIC, "java/util/Arrays", "asList", "([Ljava/lang/Object;)Ljava/util/List;");
        var node6 = new MethodInsnNode(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "(Ljava/util/Collection;)V");
        var node7 = new VarInsnNode(Opcodes.ASTORE, 1);
        var node8 = new LabelNode(label1);
        var node9 = new VarInsnNode(Opcodes.ALOAD, 1);
        var node10 = new VarInsnNode(Opcodes.ALOAD, 0);
        var node11 = new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/util/ArrayList", "add", "(Ljava/lang/Object;)Z");
        var node12 = new InsnNode(Opcodes.POP);
        var node13 = new LabelNode(label2);
        var node14 = new VarInsnNode(Opcodes.ALOAD, 1);
        var node15 = new InsnNode(Opcodes.H_PUTFIELD);
        var node16 = new TypeInsnNode(Opcodes.ANEWARRAY, clazz);
        var node17 = new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/util/ArrayList", "toArray", "([Ljava/lang/Object;)[Ljava/lang/Object;");
        var node18 = new TypeInsnNode(Opcodes.CHECKCAST, "[L" + clazz + ";");
        var node19 = new FieldInsnNode(Opcodes.PUTSTATIC, clazz, field, "[L" + clazz + ";");
        var node20 = new LabelNode(label3);
        var node21 = new VarInsnNode(Opcodes.ALOAD, 0);
        var node22 = new InsnNode(Opcodes.ARETURN);
        var node23 = new LabelNode(label4);
        insnList.add(listOf(
                node1,
                node2,
                node3,
                node4,
                node5,
                node6,
                node7,
                node8,
                node9,
                node10,
                node11,
                node12,
                node13,
                node14,
                node15,
                node16,
                node17,
                node18,
                node19,
                node20,
                node21,
                node22,
                node23
        ));
    }

    private static void createCreateConstructor(InsnList insnList, String clazz, String target, String desc) {
        clazz = clazz.replaceAll("\\.", "/");
        target = target.replaceAll("\\.", "/");
        var varNodes = new ArrayList<VarInsnNode>();
        var label0 = new Label();
        var label1 = new Label();
        var node1 = new LabelNode(label0);
        var node2 = new TypeInsnNode(Opcodes.NEW, clazz);
        var node3 = new InsnNode(Opcodes.DUP);
        var types = Arrays.stream(Type.getArgumentTypes(desc)).toList();
        var i = 0;
        for (Type type : types) {
            varNodes.add(new VarInsnNode(type.getOpcode(Opcodes.ILOAD), i));
            i++;
        }
        var node4 = new MethodInsnNode(Opcodes.INVOKESPECIAL, clazz, "<init>", desc);
        var node5 = new MethodInsnNode(Opcodes.INVOKESTATIC, target, PREFIX + "addToField", "(L" + clazz + ";)L" + clazz + ";");
        var node6 = new InsnNode(Opcodes.ARETURN);
        var node7 = new LabelNode(label1);
        insnList.add(listOf(node1, node2, node3));
        varNodes.forEach(insnList::add);
        insnList.add(listOf(node4, node5, node6, node7));
    }



    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        FabricLoader.getInstance().getEntrypoints("thonkutil:enum_api", Runnable.class).forEach(Runnable::run);
        transformClass(targetClass, targetClassName, "net.minecraft.class_1690$class_1692", "field_7724", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.BoatTypeAdder", "(Ljava/lang/String;IL" + map("net/minecraft/class_2248", true) + ";Ljava/lang/String;)V");
        transformClass(targetClass, targetClassName,  "net.minecraft.class_1799$class_5422", "field_25776", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.TooltipSectionAdder", "(Ljava/lang/String;I)V");
        List<MethodName> names = ImmutableList.of(new MethodName("method_8177", "(Lnet/minecraft/class_1792;)Z", "thonkutil$isAcceptableItem"));
        transformClass(targetClass, targetClassName, "net.minecraft.class_1886", "field_9077", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.EnchantmentTargetAdder", "(Ljava/lang/String;I)V", names);
        transformClass(targetClass, targetClassName, "net.minecraft.class_1267", "field_5804", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.DifficultyCreator", "(Ljava/lang/String;IILjava/lang/String;)V");
        transformClass(targetClass, targetClassName, "com.terraformersmc.modmenu.util.mod.Mod$Badge", "$VALUES", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.ModBadgeCreator", "(Ljava/lang/String;ILjava/lang/String;IILjava/lang/String;)V");
    }

// // access flags 0x1
//  public isAcceptableItem(Lnet/minecraft/item/Item;)Z
//   L0
//    LINENUMBER 24 L0
//    ALOAD 0
//    ALOAD 1
//    INVOKEVIRTUAL com/jab125/limeappleboat/thonkutil/enumapi/v1/api/BoatTypeAdder.isAcceptableItem2 (Lnet/minecraft/item/Item;)Z
//    IRETURN
//   L1
//    LOCALVARIABLE this Lcom/jab125/limeappleboat/thonkutil/enumapi/v1/api/BoatTypeAdder; L0 L1 0
//    LOCALVARIABLE item Lnet/minecraft/item/Item; L0 L1 1
//    MAXSTACK = 2
//    MAXLOCALS = 2

    private String getName(ClassNode clazz, MethodNode method, List<MethodName> names) {
        for (MethodName name : names) {
            if (remapDescriptor(name.descriptor()).equals(method.desc)) {
                return name.extensionName();
            }
        }
        return "";
    }

    private void abstractHook(ClassNode classNode, MethodNode method, List<MethodName> names) {
        var instructions = method.instructions;
        var label0 = new Label();
        var label1 = new Label();
        var node1 = new LabelNode(label0);
        var node2 = new LineNumberNode(92, node1);
        var node3 = new VarInsnNode(Opcodes.ALOAD, 0);
        var node4 = new VarInsnNode(Opcodes.ALOAD, 1);
        var node5 = new MethodInsnNode(Opcodes.INVOKEVIRTUAL, classNode.name, getName(classNode, method, names), method.desc);
        var node6 = new InsnNode(Type.getReturnType(method.desc).getOpcode(Opcodes.IRETURN));
        var node7 = new LabelNode(label1);
        method.instructions.add(node1);
        method.instructions.add(node2);
        method.instructions.add(node3);
        method.instructions.add(node4);
        method.instructions.add(node5);
        method.instructions.add(node6);
        method.instructions.add(node7);
        //method.instructions.add();
    }

    private void transformClass(ClassNode targetClass, String targetClassName, String enumClass, String valuesField, String surrogateClass, String desc) {
        this.transformClass(targetClass, targetClassName, enumClass, valuesField, surrogateClass, desc, null);
    }

    private void transformClass(ClassNode targetClass, String targetClassName, String enumClass, String valuesField, String surrogateClass, String desc, List<MethodName> names) {
        targetClassName = targetClassName.replaceAll("/", ".");
        enumClass = map(enumClass).replaceAll("/", ".");
        surrogateClass = surrogateClass.replaceAll("/", ".");
        System.out.println(targetClassName + ", " + enumClass + ", " + targetClassName.equals(map(enumClass)));
        if (targetClassName.equals(enumClass)) {
            targetClass.access &= ~Opcodes.ACC_ABSTRACT; // impl classes via mixin
            targetClass.fields.forEach(field -> {field.access &= ~Opcodes.ACC_SYNTHETIC;if(field.name.equals(valuesField)){field.access&=~Opcodes.ACC_PRIVATE;field.access&=~Opcodes.ACC_FINAL;field.access+=Opcodes.ACC_PUBLIC;}});
            for (MethodNode method : targetClass.methods) {
                method.access &= ~Opcodes.ACC_SYNTHETIC;
                if ((method.access & ~Opcodes.ACC_ABSTRACT) != method.access) {
                    abstractHook(targetClass, method, names);
                    method.access &= ~Opcodes.ACC_ABSTRACT;
                }
                System.out.println(method.name + ", " + method.desc);
                System.out.println(method.access);
                if (method.name.equals("<init>")) {
                    System.out.println("transforming init");
                    method.access &= ~Opcodes.ACC_PRIVATE;
                    method.access &= ~Opcodes.ACC_PUBLIC;
                    method.access += Opcodes.ACC_PUBLIC;
                    System.out.println("DESC: " + method.desc);
                    System.out.println(method.access);
                }
            }
        } else if (targetClassName.equals(surrogateClass)) {
            MethodNode node = new MethodNode();
            node.access = 0;
            node.access += Opcodes.ACC_PRIVATE;
            node.access += Opcodes.ACC_STATIC;
            node.name = "createInternal";
            node.desc = desc.substring(0, desc.length()-1) + "L" + enumClass.replaceAll("\\.", "/") + ";";

            MethodNode node2 = new MethodNode();
            node2.access = 0;
            node2.access += Opcodes.ACC_PRIVATE;
            node2.access += Opcodes.ACC_STATIC;
            node2.name = PREFIX + "addToField";
            node2.desc = "(L" + enumClass.replaceAll("\\.", "/") + ";)L" + enumClass.replaceAll("\\.", "/") + ";";
            System.out.println("NODE: " + node.desc);

            var target = surrogateClass;
            createCreateConstructor(node.instructions, enumClass, target, desc);
            methodQueues.add(new MethodQueued(node, target));
            createAddToFieldConstructor(node2.instructions, enumClass, valuesField);
            methodQueues.add(new MethodQueued(node2, target));
        }
        methodQueues.forEach(a -> a.add(targetClass));
    }

    private static String map(String clazz) {
        return map(clazz, false);
    }

    private static String map(String clazz, boolean slash) {
        var a = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", clazz.replaceAll("/", "."));
        System.out.println(WordUtils.capitalize(FabricLoader.getInstance().getMappingResolver().getCurrentRuntimeNamespace()) + " name: " + a);
        return slash ? a.replaceAll("\\.", "/") : a;
    }

    private static String remap(String clazz) {
        return remap(clazz, false);
    }

    private static String remap(String clazz, boolean slash) {
        var a = FabricLoader.getInstance().getMappingResolver().unmapClassName("intermediary", clazz.replaceAll("/", "."));
        System.out.println("Intermediary name: " + a);
        return slash ? a.replaceAll("\\.", "/") : a;
    }

    private static InsnList listOf(AbstractInsnNode... nodes) {
        InsnList list = new InsnList();
        for (AbstractInsnNode node : nodes)
            list.add(node);
        return list;
    }
}
