//var d = new MethodNode(access, name, descriptor, signature, exceptions);
//            d.instructions.clear();
//            if (d.localVariables != null)
//            d.localVariables.clear();
//            d.access |= Opcodes.ACC_SYNCHRONIZED;
//            if (d.tryCatchBlocks != null)
//            {
//                d.tryCatchBlocks.clear();
//            }
//            if (d.visibleLocalVariableAnnotations != null)
//            {
//                d.visibleLocalVariableAnnotations.clear();
//            }
//            if (d.invisibleLocalVariableAnnotations != null)
//            {
//                d.invisibleLocalVariableAnnotations.clear();
//            }
//            InstructionAdapter ins = new InstructionAdapter(d);
//
//            int vars = 0;
//            var args = Type.getArgumentTypes(d.desc);
//            for (Type arg : args)
//                vars += arg.getSize();
//
//
//            Type[] ctrArgs = new Type[args.length + 1];
//            ctrArgs[0] = STRING;
//            ctrArgs[1] = Type.INT_TYPE;
//            for (int x = 1; x < args.length; x++)
//                ctrArgs[1 + x] = args[x];
//
//
//            var classType = Type.getType("Lnet/minecraft/entity/vehicle/BoatEntity$Type");
//            Type array = Type.getType("[" + classType.getDescriptor());
//            String desc = Type.getMethodDescriptor(Type.VOID_TYPE, ctrArgs);
//
//            {
//                vars += 1; //int x
//                Label for_start = new Label();
//                Label for_condition = new Label();
//                Label for_inc = new Label();
//
//
//                ins.iconst(0);
//                ins.store(vars, Type.INT_TYPE);
//                ins.goTo(for_condition);
//                //if (!VALUES[x].name().equalsIgnoreCase(name)) goto for_inc
//                ins.mark(for_start);
//                ins.getstatic(classType.getInternalName(), d.name, d.desc);
//                ins.load(vars, Type.INT_TYPE);
//                ins.aload(array);
//                ins.invokevirtual(ENUM.getInternalName(), "name", NAME_DESC, false);
//                ins.load(0, STRING);
//                ins.invokevirtual(STRING.getInternalName(), "equalsIgnoreCase", EQUALS_DESC, false);
//                ins.ifeq(for_inc);
//                //return VALUES[x];
//                ins.getstatic(classType.getInternalName(), d.name, d.desc);
//                ins.load(vars, Type.INT_TYPE);
//                ins.aload(array);
//                ins.areturn(classType);
//                //x++
//                ins.mark(for_inc);
//                ins.iinc(vars, 1);
//                //if (x < VALUES.length) goto for_start
//                ins.mark(for_condition);
//                ins.load(vars, Type.INT_TYPE);
//                ins.getstatic(classType.getInternalName(), d.name, d.desc);
//                ins.arraylength();
//                ins.ificmplt(for_start);
//            }
//
//            {
//                vars += 1; //enum ret;
//                //ret = new ThisType(name, VALUES.length, args..)
//                ins.anew(classType);
//                ins.dup();
//                ins.load(0, STRING);
//                ins.getstatic(classType.getInternalName(), d.name, d.desc);
//                ins.arraylength();
//                int idx = 1;
//                for (int x = 1; x < args.length; x++)
//                {
//                    ins.load(idx, args[x]);
//                    idx += args[x].getSize();
//                }
//
//                ins.invokespecial(classType.getInternalName(), "<init>", desc, false);
//                ins.store(vars, classType);
//                // VALUES = ArrayUtils.add(VALUES, ret)
//                ins.getstatic(classType.getInternalName(), d.name, d.desc);
//                ins.load(vars, classType);
//                ins.invokestatic(ARRAY_UTILS.getInternalName(), "add", ADD_DESC, false);
//                ins.checkcast(array);
//                ins.putstatic(classType.getInternalName(), d.name, d.desc);
//                //EnumHelper.cleanEnumCache(ThisType.class)
//                ins.visitLdcInsn(classType);
//                ins.invokestatic(UNSAFE_HACKS.getInternalName(), "cleanEnumCache", CLEAN_DESC, false);
//                //init ret
//                ins.load(vars, classType);
//                ins.invokeinterface(MARKER_IFACE.getInternalName(), "init", "()V");
//                //return ret
//                ins.load(vars, classType);
//                ins.areturn(classType);
//            }
//            return d;

package com.jab125.thonkutil.misc.asm;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.security.CodeSource;

import static org.objectweb.asm.Opcodes.*;

public abstract class ASM extends ClassVisitor {

    public abstract String fieldName();

    protected ASM(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name);
        System.out.println(access);
        access &= ~Opcodes.ACC_FINAL;
        access &= ~ACC_ABSTRACT;
        System.out.println(access);
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitPermittedSubclass(String permittedSubclass) {
        super.visitPermittedSubclass(permittedSubclass);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println(descriptor);
        System.out.println(name);
        System.out.println(access);
        if (name.equals("<init>") && !(this instanceof EnchantmentASM)) {
            if ((access & ACC_PRIVATE) != 0)
            access = access - Opcodes.ACC_PRIVATE;
            if ((access & ACC_PUBLIC) == 0)
            access = access + Opcodes.ACC_PUBLIC;
        }
        if ((access & Opcodes.ACC_SYNTHETIC) != 0) {
            access = access - Opcodes.ACC_SYNTHETIC;
        }
        System.out.println(access);
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if (!name.equals(fieldName())) return super.visitField(access, name, descriptor, signature, value);
        System.out.println(name);
        access = Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC;
        System.out.println(access);
        return super.visitField(access, name, descriptor, signature, value);
    }
}
