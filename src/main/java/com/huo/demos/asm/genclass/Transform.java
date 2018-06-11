package com.huo.demos.asm.genclass;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.huo.demos.asm.Test;

public class Transform {
    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader("com.huo.demos.asm.Test");
        ClassWriter cw = new ClassWriter(cr, 0);
        ChangeVersionAdapter cva = new ChangeVersionAdapter(cw);
        cr.accept(cva, 0);
        byte[] b = cw.toByteArray();
    }
}
