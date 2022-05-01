package com.jab125.thonkutil.misc.asm;

import org.objectweb.asm.*;

public class EnchantmentTargetPatch extends Patch{

    static void patch() throws Exception {
        EnchantmentASM.patchClass("net.minecraft.enchantment.EnchantmentTarget", "field_9077", "com.jab125.thonkutil.misc.api.v1.CustomEnchantmentTarget", extension());
        //patchClass("com.jab125.thonkutil.misc.api.v1.CustomEnchantmentTarget$1", extension$1());
    }

    public static byte[] extension() throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(V17, ACC_PUBLIC | ACC_SUPER, "com/jab125/thonkutil/misc/api/v1/CustomEnchantmentTarget", null, "java/lang/Object", null);

        classWriter.visitSource("CustomEnchantmentTarget.java", null);

        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(11, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/jab125/thonkutil/misc/api/v1/CustomEnchantmentTarget;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "create", "(Ljava/lang/String;Ljava/util/function/Predicate;)Lnet/minecraft/enchantment/EnchantmentTarget;", "(Ljava/lang/String;Ljava/util/function/Predicate<Lnet/minecraft/item/Item;>;)Lnet/minecraft/enchantment/EnchantmentTarget;", null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(13, label0);
            methodVisitor.visitTypeInsn(NEW, "java/util/ArrayList");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitFieldInsn(GETSTATIC, "net/minecraft/enchantment/EnchantmentTarget", "field_9077", "[Lnet/minecraft/enchantment/EnchantmentTarget;");
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Arrays", "asList", "([Ljava/lang/Object;)Ljava/util/List;", false);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "(Ljava/util/Collection;)V", false);
            methodVisitor.visitVarInsn(ASTORE, 2);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(14, label1);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "size", "()I", false);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(ISUB);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "get", "(I)Ljava/lang/Object;", false);
            methodVisitor.visitTypeInsn(CHECKCAST, "net/minecraft/enchantment/EnchantmentTarget");
            methodVisitor.visitVarInsn(ASTORE, 3);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(15, label2);
            methodVisitor.visitTypeInsn(NEW, "net/minecraft/enchantment/EnchantmentTarget");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 3);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/enchantment/EnchantmentTarget", "ordinal", "()I", false);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(IADD);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "net/minecraft/enchantment/EnchantmentTarget", "<init>", "(Ljava/lang/String;ILjava/util/function/Predicate;)V", false);
            methodVisitor.visitVarInsn(ASTORE, 4);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(16, label3);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "add", "(Ljava/lang/Object;)Z", false);
            methodVisitor.visitInsn(POP);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLineNumber(17, label4);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitTypeInsn(ANEWARRAY, "net/minecraft/enchantment/EnchantmentTarget");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "toArray", "([Ljava/lang/Object;)[Ljava/lang/Object;", false);
            methodVisitor.visitTypeInsn(CHECKCAST, "[Lnet/minecraft/enchantment/EnchantmentTarget;");
            methodVisitor.visitFieldInsn(PUTSTATIC, "net/minecraft/enchantment/EnchantmentTarget", "field_9077", "[Lnet/minecraft/enchantment/EnchantmentTarget;");
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLineNumber(18, label5);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitInsn(ARETURN);
            Label label6 = new Label();
            methodVisitor.visitLabel(label6);
            methodVisitor.visitLocalVariable("internalName", "Ljava/lang/String;", null, label0, label6, 0);
            methodVisitor.visitLocalVariable("pred", "Ljava/util/function/Predicate;", "Ljava/util/function/Predicate<Lnet/minecraft/item/Item;>;", label0, label6, 1);
            methodVisitor.visitLocalVariable("targets", "Ljava/util/ArrayList;", "Ljava/util/ArrayList<Lnet/minecraft/enchantment/EnchantmentTarget;>;", label1, label6, 2);
            methodVisitor.visitLocalVariable("last", "Lnet/minecraft/enchantment/EnchantmentTarget;", null, label2, label6, 3);
            methodVisitor.visitLocalVariable("target", "Lnet/minecraft/enchantment/EnchantmentTarget;", null, label3, label6, 4);
            methodVisitor.visitMaxs(5, 5);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
