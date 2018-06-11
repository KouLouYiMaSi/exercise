package com.huo.demos.asm.genclass;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
/**
 * 利用ClassWriter生成一个接口类型,要求内存里得有这个原型？不然定义之后没发使用？
 *
 * @author bjhuoqingyuan
 *
 */
public class GenCode {
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE, "com/huo/demos/asm/genclass/Comparable",
                null, "java/lang/Object", new String[] {  });
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS", "I", null, new Integer(-1))
                .visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL", "I", null, new Integer(0))
                .visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER", "I", null, new Integer(1))
                .visitEnd();
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null)
                .visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        MyClassLoader myClassLoader = new MyClassLoader();
        Class c = myClassLoader.defineClassForName("com.huo.demos.asm.genclass.Comparable", b);
        System.out.println(c.getName());
    }
}
