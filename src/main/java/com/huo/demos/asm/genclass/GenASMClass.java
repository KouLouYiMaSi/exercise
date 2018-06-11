package com.huo.demos.asm.genclass;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class GenASMClass {
    public static ClassWriter createClassWriter(String className) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null);
        MethodVisitor constructor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        constructor.visitVarInsn(Opcodes.ALOAD, 0);
        constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        constructor.visitInsn(Opcodes.RETURN);
        constructor.visitMaxs(1, 1);
        constructor.visitEnd();
        return cw;
    }

    public static byte[] createVoidMethod(String className, String message) throws Exception {
        ClassWriter cw = createClassWriter(className.replace('.', '/'));
        MethodVisitor runMethod = cw.visitMethod(Opcodes.ACC_PUBLIC, "run", "()V", null, null);
        runMethod.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        runMethod.visitLdcInsn(message);
        runMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V",
                false);
        runMethod.visitInsn(Opcodes.RETURN);
        runMethod.visitMaxs(1, 1);
        runMethod.visitEnd();
        return cw.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        String className = "com.huo.demos.asm.genclass.Example";
        byte[] classData = createVoidMethod(className, "待打印的例子");
        Class<?> clazz = new MyClassLoader().defineClassForName(className, classData);
        clazz.getMethods()[0].invoke(clazz.newInstance());
        System.out.println(clazz.getName());
    }
}