package com.huo.demos.asm.genclass;

import java.io.IOException;

import org.objectweb.asm.*;

public class AddFieldToClass {
    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader("com.huo.demos.asm.Test");
        ClassWriter cw = new ClassWriter(cr,0);
        AddFieldAdapter afa = new AddFieldAdapter(cw, Opcodes.ACC_PUBLIC, "addedField", "the field is adding");
        cr.accept(cw, 0);
    }
}
