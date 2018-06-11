package com.huo.demos.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
/**
 * ClassVisitor可以访问一个编译好的类的所有元素，ClassPrinter就根据这个访问打印出来所有元素
 * @author bjhuoqingyuan
 *
 */
public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(Opcodes.ASM4);
    }

    /**
     * 这个方法会被第一个执行
     *
     */
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }
    /**
     * 这个方法第二个被执行
     */
    public void visitSource(String source, String debug) {
    }
    /**
     * 第三个被执行
     */
    public void visitOuterClass(String owner, String name, String desc) {
    }
    /**
     * 并列第四个执行
     */
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return null;
    }
    /**
     * 并列第四个执行
     */
    public void visitAttribute(Attribute attr) {
    }
    /**
     * 并列第五个执行
     */
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
    }
    /**
     * 并列第五个执行
     */
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println(" " + desc + " " + name);
        return null;
    }
    /**
     * 并列第五个执行
     */
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }
    /**
     * 最后被执行
     */
    public void visitEnd() {
        System.out.println("}");
    }
}