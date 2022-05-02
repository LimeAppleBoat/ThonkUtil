package com.jab125.thonkutil.misc.asm;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.ASMifier;

import java.io.IOException;
import java.nio.file.Files;
import java.security.CodeSource;

public class Patch implements Opcodes {
    public static void main(String[] args) throws IOException {
        //ASMifier.main(new String[]{"/Users/josephyap/Documents/GitHub/ThonkUtil/thonkutil-misc-api-v1/build/classes/java/main/com/jab125/thonkutil/misc/api/v1/CustomEnchantmentTarget.class"});
        //ASMifier.main(new String[]{"/Users/josephyap/Documents/GitHub/ThonkUtil/thonkutil-misc-api-v1/build/classes/java/main/com/jab125/thonkutil/misc/asm/CustomEnchantmentTarget2.class"});
        ASMifier.main(new String[]{"/Users/josephyap/Documents/GitHub/ThonkUtil/thonkutil-misc-api-v1/build/classes/java/main/com/jab125/thonkutil/misc/api/v1/CustomModBadge.class"});
    }
    public static void patchClass(String className, byte[] bytes) throws Exception{

        Files.write(FabricLoader.getInstance().getGameDir().resolve(className + ".class"), bytes);
        var q = Class.forName("net.fabricmc.loader.impl.launch.knot.KnotClassLoader").getDeclaredMethod("defineClassFwd", String.class, byte[].class, int.class, int.class, CodeSource.class);
        q.setAccessible(true);
        q.invoke(ASM.class.getClassLoader(), className, bytes, 0, bytes.length, null);

        //q.invoke(ASM.class.getClassLoader(), className, bytes, 0, bytes.length, null);
    }
    public static void patchClass(String className, String fieldName, String extenderName, byte[] extender) throws Exception {
        ClassReader cr = new ClassReader(className);
        ClassWriter cw = new ClassWriter(cr, 0);
        cr.accept(new ASM(FabricLoaderImpl.ASM_VERSION, cw) {
            @Override
            public String fieldName() {
                return fieldName;
            }
        }, 0);

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
