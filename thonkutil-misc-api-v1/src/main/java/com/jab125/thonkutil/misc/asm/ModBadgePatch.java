package com.jab125.thonkutil.misc.asm;

import org.objectweb.asm.*;

public class ModBadgePatch extends Patch implements Opcodes {

    static void patch() throws Exception {
        Patch.patchClass("com.terraformersmc.modmenu.util.mod.Mod$Badge", "$VALUES", "com.jab125.thonkutil.misc.api.v1.CustomModBadge", extension());
    }
    
    public static byte[] extension() throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(V17, ACC_PUBLIC | ACC_SUPER, "com/jab125/thonkutil/misc/api/v1/CustomModBadge", null, "java/lang/Object", null);

        classWriter.visitSource("CustomModBadge.java", null);

        classWriter.visitInnerClass("java/lang/StackWalker$Option", "java/lang/StackWalker", "Option", ACC_PUBLIC | ACC_FINAL | ACC_STATIC | ACC_ENUM);

        classWriter.visitInnerClass("java/lang/invoke/MethodHandles$Lookup", "java/lang/invoke/MethodHandles", "Lookup", ACC_PUBLIC | ACC_FINAL | ACC_STATIC);

        {
            fieldVisitor = classWriter.visitField(ACC_PRIVATE | ACC_FINAL | ACC_STATIC, "translatable", "Ljava/util/ArrayList;", "Ljava/util/ArrayList<Loshi/util/tuples/Pair<Ljava/lang/String;Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;>;>;", null);
            fieldVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(12, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/jab125/thonkutil/misc/api/v1/CustomModBadge;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "getTranslatable", "()Ljava/util/ArrayList;", "()Ljava/util/ArrayList<Loshi/util/tuples/Pair<Ljava/lang/String;Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;>;>;", null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(17, label0);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/StackWalker$Option", "RETAIN_CLASS_REFERENCE", "Ljava/lang/StackWalker$Option;");
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/StackWalker", "getInstance", "(Ljava/lang/StackWalker$Option;)Ljava/lang/StackWalker;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StackWalker", "getCallerClass", "()Ljava/lang/Class;", false);
            methodVisitor.visitLdcInsn(Type.getType("Lcom/terraformersmc/modmenu/util/mod/ModSearch;"));
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "equals", "(Ljava/lang/Object;)Z", false);
            Label label1 = new Label();
            methodVisitor.visitJumpInsn(IFNE, label1);
            methodVisitor.visitTypeInsn(NEW, "java/lang/IllegalStateException");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/StackWalker$Option", "RETAIN_CLASS_REFERENCE", "Ljava/lang/StackWalker$Option;");
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/StackWalker", "getInstance", "(Ljava/lang/StackWalker$Option;)Ljava/lang/StackWalker;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StackWalker", "getCallerClass", "()Ljava/lang/Class;", false);
            methodVisitor.visitInvokeDynamicInsn("makeConcatWithConstants", "(Ljava/lang/Class;)Ljava/lang/String;", new Handle(Opcodes.H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false), new Object[]{"\u0001 shouldn't call this!"});
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalStateException", "<init>", "(Ljava/lang/String;)V", false);
            methodVisitor.visitInsn(ATHROW);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(18, label1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitFieldInsn(GETSTATIC, "com/jab125/thonkutil/misc/api/v1/CustomModBadge", "translatable", "Ljava/util/ArrayList;");
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitMaxs(3, 0);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "create", "(Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;)Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(37, label0);
            methodVisitor.visitTypeInsn(NEW, "java/util/ArrayList");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitFieldInsn(GETSTATIC, "com/terraformersmc/modmenu/util/mod/Mod$Badge", "$VALUES", "[Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;");
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Arrays", "asList", "([Ljava/lang/Object;)Ljava/util/List;", false);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "(Ljava/util/Collection;)V", false);
            methodVisitor.visitVarInsn(ASTORE, 5);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(38, label1);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "size", "()I", false);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(ISUB);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "get", "(I)Ljava/lang/Object;", false);
            methodVisitor.visitTypeInsn(CHECKCAST, "com/terraformersmc/modmenu/util/mod/Mod$Badge");
            methodVisitor.visitVarInsn(ASTORE, 6);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(39, label2);
            methodVisitor.visitTypeInsn(NEW, "com/terraformersmc/modmenu/util/mod/Mod$Badge");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 6);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/terraformersmc/modmenu/util/mod/Mod$Badge", "ordinal", "()I", false);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(IADD);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitVarInsn(ILOAD, 3);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/terraformersmc/modmenu/util/mod/Mod$Badge", "<init>", "(Ljava/lang/String;ILjava/lang/String;IILjava/lang/String;)V", false);
            methodVisitor.visitVarInsn(ASTORE, 7);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(40, label3);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitVarInsn(ALOAD, 7);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "add", "(Ljava/lang/Object;)Z", false);
            methodVisitor.visitInsn(POP);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLineNumber(41, label4);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitTypeInsn(ANEWARRAY, "com/terraformersmc/modmenu/util/mod/Mod$Badge");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "toArray", "([Ljava/lang/Object;)[Ljava/lang/Object;", false);
            methodVisitor.visitTypeInsn(CHECKCAST, "[Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;");
            methodVisitor.visitFieldInsn(PUTSTATIC, "com/terraformersmc/modmenu/util/mod/Mod$Badge", "$VALUES", "[Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;");
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLineNumber(42, label5);
            methodVisitor.visitVarInsn(ALOAD, 7);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "com/terraformersmc/modmenu/util/mod/Mod$Badge", "lambda$static$0", "(Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;)V", false);
            Label label6 = new Label();
            methodVisitor.visitLabel(label6);
            methodVisitor.visitLineNumber(43, label6);
            methodVisitor.visitVarInsn(ALOAD, 7);
            methodVisitor.visitInsn(ARETURN);
            Label label7 = new Label();
            methodVisitor.visitLabel(label7);
            methodVisitor.visitLocalVariable("internalName", "Ljava/lang/String;", null, label0, label7, 0);
            methodVisitor.visitLocalVariable("translationKey", "Ljava/lang/String;", null, label0, label7, 1);
            methodVisitor.visitLocalVariable("outlineColor", "I", null, label0, label7, 2);
            methodVisitor.visitLocalVariable("fillColor", "I", null, label0, label7, 3);
            methodVisitor.visitLocalVariable("key", "Ljava/lang/String;", null, label0, label7, 4);
            methodVisitor.visitLocalVariable("types", "Ljava/util/ArrayList;", "Ljava/util/ArrayList<Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;>;", label1, label7, 5);
            methodVisitor.visitLocalVariable("last", "Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;", null, label2, label7, 6);
            methodVisitor.visitLocalVariable("type", "Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;", null, label3, label7, 7);
            methodVisitor.visitMaxs(8, 8);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "create", "(Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;)Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(47, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitVarInsn(ILOAD, 3);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "com/jab125/thonkutil/misc/api/v1/CustomModBadge", "create", "(Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;)Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;", false);
            methodVisitor.visitVarInsn(ASTORE, 6);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(48, label1);
            methodVisitor.visitFieldInsn(GETSTATIC, "com/jab125/thonkutil/misc/api/v1/CustomModBadge", "translatable", "Ljava/util/ArrayList;");
            methodVisitor.visitTypeInsn(NEW, "oshi/util/tuples/Pair");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitVarInsn(ALOAD, 6);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "oshi/util/tuples/Pair", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "add", "(Ljava/lang/Object;)Z", false);
            methodVisitor.visitInsn(POP);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(49, label2);
            methodVisitor.visitVarInsn(ALOAD, 6);
            methodVisitor.visitInsn(ARETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("internalName", "Ljava/lang/String;", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("translationKey", "Ljava/lang/String;", null, label0, label3, 1);
            methodVisitor.visitLocalVariable("outlineColor", "I", null, label0, label3, 2);
            methodVisitor.visitLocalVariable("fillColor", "I", null, label0, label3, 3);
            methodVisitor.visitLocalVariable("key", "Ljava/lang/String;", null, label0, label3, 4);
            methodVisitor.visitLocalVariable("searchKey", "Ljava/lang/String;", null, label0, label3, 5);
            methodVisitor.visitLocalVariable("type", "Lcom/terraformersmc/modmenu/util/mod/Mod$Badge;", null, label1, label3, 6);
            methodVisitor.visitMaxs(5, 7);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(14, label0);
            methodVisitor.visitTypeInsn(NEW, "java/util/ArrayList");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
            methodVisitor.visitFieldInsn(PUTSTATIC, "com/jab125/thonkutil/misc/api/v1/CustomModBadge", "translatable", "Ljava/util/ArrayList;");
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(2, 0);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
