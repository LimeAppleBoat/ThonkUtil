package com.jab125.thonkutil.misc.asm;

import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.ARETURN;

public class BoatPatch {

    static void patch() throws Exception {
        Patch.patchClass("net.minecraft.entity.vehicle.BoatEntity$Type", "field_7724", "com.jab125.thonkutil.misc.api.v1.CustomBoatType", extension());
    }

    public static byte[] extension() {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

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
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "create", "(Ljava/lang/String;Lnet/minecraft/block/Block;Ljava/lang/String;)Lnet/minecraft/entity/vehicle/BoatEntity$Type;", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(11, label0);
            methodVisitor.visitTypeInsn(NEW, "java/util/ArrayList");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitFieldInsn(GETSTATIC, "net/minecraft/entity/vehicle/BoatEntity$Type", "field_7724", "[Lnet/minecraft/entity/vehicle/BoatEntity$Type;");
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
            methodVisitor.visitTypeInsn(CHECKCAST, "net/minecraft/entity/vehicle/BoatEntity$Type");
            methodVisitor.visitVarInsn(ASTORE, 4);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(13, label2);
            methodVisitor.visitTypeInsn(NEW, "net/minecraft/entity/vehicle/BoatEntity$Type");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/vehicle/BoatEntity$Type", "ordinal", "()I", false);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(IADD);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "net/minecraft/entity/vehicle/BoatEntity$Type", "<init>", "(Ljava/lang/String;ILnet/minecraft/block/Block;Ljava/lang/String;)V", false);
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
            methodVisitor.visitTypeInsn(ANEWARRAY, "net/minecraft/entity/vehicle/BoatEntity$Type");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "toArray", "([Ljava/lang/Object;)[Ljava/lang/Object;", false);
            methodVisitor.visitTypeInsn(CHECKCAST, "[Lnet/minecraft/entity/vehicle/BoatEntity$Type;");
            methodVisitor.visitFieldInsn(PUTSTATIC, "net/minecraft/entity/vehicle/BoatEntity$Type", "field_7724", "[Lnet/minecraft/entity/vehicle/BoatEntity$Type;");
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLineNumber(16, label5);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitInsn(ARETURN);
            Label label6 = new Label();
            methodVisitor.visitLabel(label6);
            methodVisitor.visitLocalVariable("internalName", "Ljava/lang/String;", null, label0, label6, 0);
            methodVisitor.visitLocalVariable("baseBlock", "Lnet/minecraft/block/Block;", null, label0, label6, 1);
            methodVisitor.visitLocalVariable("name", "Ljava/lang/String;", null, label0, label6, 2);
            methodVisitor.visitLocalVariable("types", "Ljava/util/ArrayList;", "Ljava/util/ArrayList<Lnet/minecraft/entity/vehicle/BoatEntity$Type;>;", label1, label6, 3);
            methodVisitor.visitLocalVariable("last", "Lnet/minecraft/entity/vehicle/BoatEntity$Type;", null, label2, label6, 4);
            methodVisitor.visitLocalVariable("type", "Lnet/minecraft/entity/vehicle/BoatEntity$Type;", null, label3, label6, 5);
            methodVisitor.visitMaxs(6, 6);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
