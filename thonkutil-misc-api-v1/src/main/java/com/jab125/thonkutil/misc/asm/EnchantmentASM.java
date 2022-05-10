package com.jab125.thonkutil.misc.asm;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.minecraft.obfuscate.DontObfuscate;
import org.objectweb.asm.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.security.CodeSource;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class EnchantmentASM extends ASM implements Opcodes{
    protected EnchantmentASM(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public String fieldName() {
        return "field_9077";
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (name.equals(FabricLoader.getInstance().getMappingResolver().mapMethodName("intermediary", "net.minecraft.class_1886", "method_8177", "(Lnet/minecraft/class_1792;)Z")) && (access & ACC_ABSTRACT) != 0) {
            return null;
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    public static void patchClass(String className, String fieldName, String extenderName, byte[] extender) throws Exception {
        System.out.println("Patched Class Name: " + className);
        var qs = ClassReader.class.getDeclaredMethod("readStream", InputStream.class, boolean.class);
        qs.setAccessible(true);

        var slkj = DontObfuscate.class.getClassLoader().getResource(className.replace('.', '/') + ".class");
        System.out.println(slkj);
        //System.exit(-1);
        var rtf = DontObfuscate.class.getClassLoader().getResourceAsStream(className.replace('.', '/') + ".class");
        if (rtf == null) throw new RuntimeException("QUANTUM PHYSICS!!!!");
        //if (true) throw new IllegalStateException(slkj + ", " + rtf);
        byte[] fvg = (byte[]) qs.invoke(null, rtf, true);

        ClassReader cr = new ClassReader(fvg);
        ClassWriter cw = new ClassWriter(cr, 0);
        FieldVisitor fieldVisitor;
        MethodVisitor methodVisitor;
        cr.accept(new EnchantmentASM(FabricLoaderImpl.ASM_VERSION, cw), 0);
        AnnotationVisitor annotationVisitor0;
        var enchantmentTargetName = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1886").replaceAll("\\.", "/");
        var itemName = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1792").replaceAll("\\.", "/");

        //cw.visit(V17, ACC_PUBLIC | ACC_SUPER, "L" + enchantmentTargetName + ";", null, "java/lang/Object", null);

        cw.visitSource("EnchantmentTarget.java", null);

        {
            fieldVisitor = cw.visitField(ACC_PRIVATE, "func", "Ljava/util/function/Predicate;", "Ljava/util/function/Predicate<L" + itemName + ";>;", null);
            fieldVisitor.visitEnd();
        }
        {
            methodVisitor = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/String;ILjava/util/function/Predicate;)V", "(Ljava/util/function/Predicate<L" + itemName + ";>;)V", null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(16, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Enum", "<init>", "(Ljava/lang/String;I)V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(17, label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 3);
            methodVisitor.visitFieldInsn(PUTFIELD, enchantmentTargetName, "func", "Ljava/util/function/Predicate;");
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(18, label2);
            methodVisitor.visitInsn(RETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "L" + enchantmentTargetName + ";", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("function", "Ljava/util/function/Predicate;", "Ljava/util/function/Predicate<L" + itemName + ";>;", label0, label3, 3);
            methodVisitor.visitMaxs(3, 4);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = cw.visitMethod(ACC_PUBLIC, "isAcceptableItem", "(L" + itemName + ";)Z", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(20, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, enchantmentTargetName, "func", "Ljava/util/function/Predicate;");
            Label label1 = new Label();
            methodVisitor.visitJumpInsn(IFNULL, label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, enchantmentTargetName, "func", "Ljava/util/function/Predicate;");
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/function/Predicate", "test", "(Ljava/lang/Object;)Z", true);
            methodVisitor.visitJumpInsn(IFEQ, label1);
            methodVisitor.visitInsn(ICONST_1);
            Label label2 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label2);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {Opcodes.INTEGER});
            methodVisitor.visitInsn(IRETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "L" + enchantmentTargetName + ";", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("item", "L" + itemName + ";", null, label0, label3, 1);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        cw.visitEnd();
        //cw.visitMethod(ACC_PUBLIC, "isAcceptableItem", "(L" + itemName + ";)Z", null, new String[0]);

        //cw.
        byte[] bytes = cw.toByteArray();

        Files.write(FabricLoader.getInstance().getGameDir().resolve(className + ".class"), bytes);
        var e = ASM.class.getClassLoader();
        System.out.println(e.getClass().toString());

        var q = Class.forName("net.fabricmc.loader.impl.launch.knot.KnotClassLoader").getDeclaredMethod("defineClassFwd", String.class, byte[].class, int.class, int.class, CodeSource.class);
        q.setAccessible(true);
        q.invoke(ASM.class.getClassLoader(), className, bytes, 0, bytes.length, null);

        Files.write(FabricLoader.getInstance().getGameDir().resolve(extenderName + ".class"), extender);

        q.invoke(ASM.class.getClassLoader(), extenderName, extender, 0, extender.length, null);
    }
}
