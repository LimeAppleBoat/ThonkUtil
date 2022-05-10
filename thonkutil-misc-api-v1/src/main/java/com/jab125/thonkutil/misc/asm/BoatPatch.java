package com.jab125.thonkutil.misc.asm;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.ARETURN;

public class BoatPatch {

    static void patch() throws Exception {
        var target = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1690$class_1692");
        Patch.patchClass(target, "field_7724", "com.jab125.thonkutil.misc.api.v1.CustomBoatType", extension());
    }

    public static byte[] extension() {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        var boatTargetName = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1690$class_1692").replaceAll("\\.", "/");
        var itemTargetName = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1792").replaceAll("\\.", "/");
        var blockTargetName = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_2248").replaceAll("\\.", "/");


        classWriter.visit(V17, ACC_PUBLIC | ACC_SUPER, "com/jab125/thonkutil/misc/api/v1/CustomBoatType", null, "java/lang/Object", null);

        classWriter.visitSource("CustomBoatType.java", null);

        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(9, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/jab125/thonkutil/misc/api/v1/CustomBoatType;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "create", "(Ljava/lang/String;L" + blockTargetName + ";Ljava/lang/String;)L" + boatTargetName + ";", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(11, label0);
            methodVisitor.visitTypeInsn(NEW, "java/util/ArrayList");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitFieldInsn(GETSTATIC, boatTargetName, "field_7724", "[L" + boatTargetName + ";");
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Arrays", "asList", "([Ljava/lang/Object;)Ljava/util/List;", false);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "(Ljava/util/Collection;)V", false);
            methodVisitor.visitVarInsn(ASTORE, 3);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(12, label1);
            methodVisitor.visitVarInsn(ALOAD, 3);
            methodVisitor.visitVarInsn(ALOAD, 3);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "size", "()I", false);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(ISUB);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "get", "(I)Ljava/lang/Object;", false);
            methodVisitor.visitTypeInsn(CHECKCAST, boatTargetName);
            methodVisitor.visitVarInsn(ASTORE, 4);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(13, label2);
            methodVisitor.visitTypeInsn(NEW, boatTargetName);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, boatTargetName, "ordinal", "()I", false);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(IADD);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, boatTargetName, "<init>", "(Ljava/lang/String;IL" + blockTargetName + ";Ljava/lang/String;)V", false);
            methodVisitor.visitVarInsn(ASTORE, 5);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(14, label3);
            methodVisitor.visitVarInsn(ALOAD, 3);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "add", "(Ljava/lang/Object;)Z", false);
            methodVisitor.visitInsn(POP);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLineNumber(15, label4);
            methodVisitor.visitVarInsn(ALOAD, 3);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitTypeInsn(ANEWARRAY, boatTargetName);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "toArray", "([Ljava/lang/Object;)[Ljava/lang/Object;", false);
            methodVisitor.visitTypeInsn(CHECKCAST, "[L" + boatTargetName + ";");
            methodVisitor.visitFieldInsn(PUTSTATIC, boatTargetName, "field_7724", "[L" + boatTargetName + ";");
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLineNumber(16, label5);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitInsn(ARETURN);
            Label label6 = new Label();
            methodVisitor.visitLabel(label6);
            methodVisitor.visitLocalVariable("internalName", "Ljava/lang/String;", null, label0, label6, 0);
            methodVisitor.visitLocalVariable("baseBlock", "L" + blockTargetName + ";", null, label0, label6, 1);
            methodVisitor.visitLocalVariable("name", "Ljava/lang/String;", null, label0, label6, 2);
            methodVisitor.visitLocalVariable("types", "Ljava/util/ArrayList;", "Ljava/util/ArrayList<L" + boatTargetName + ";>;", label1, label6, 3);
            methodVisitor.visitLocalVariable("last", "L" + boatTargetName + ";", null, label2, label6, 4);
            methodVisitor.visitLocalVariable("type", "L" + boatTargetName + ";", null, label3, label6, 5);
            methodVisitor.visitMaxs(6, 6);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
