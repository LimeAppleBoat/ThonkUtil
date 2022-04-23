package com.jab125.thonkutil.misc.impl.v1;

import com.jab125.thonkutil.misc.asm.RuntimeEnumExtender;
import com.jab125.thonkutil.misc.impl.v1.mixin.BoatTypeMixin;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.entity.vehicle.BoatEntity;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

import java.lang.reflect.Method;

public class EnumExtend implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        for (Method method : BoatEntity.Type.class.getMethods()) {
            System.out.println("Method " + method.getName());
            System.out.println(Type.getMethodDescriptor(method));
        }
        Type boat_enum = Type.getMethodType("L" + FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1690$class_1692").replaceAll("\\.","/") + ";");
        ClassNode node = new ClassNode(Opcodes.ASM7);
        node.name = boat_enum.getInternalName();
        node.signature = "L" + FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1690$class_1692").replaceAll("\\.","/") + ";";
        node.superName = "java/lang/Enum";
        node.version = Opcodes.V17;
        node.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_ENUM;
        System.out.println(node.name);
        System.out.println(node.signature);
        System.out.println(node.access);
        System.out.println(node.version);
        System.out.println(node.superName);
        System.out.println("_____");
        node.visit(node.version, node.access, node.name, node.signature, node.superName, new String[]{"com/jab125/thonkutil/misc/impl/v1/ExtendableEnum"});
        //node.visitMethod(Opcodes.ACC_PUBLIC, "create", );
        new RuntimeEnumExtender().processClassWithFlags(node, boat_enum);
    }
}
