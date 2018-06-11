package com.huo.demos.asm;

import java.io.IOException;

import org.objectweb.asm.ClassReader;

public class Test {
    private String s = "";
    public static void main(String[] args) {
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = null;
        try {
            cr = new ClassReader("java.lang.Runnable");
        } catch (IOException e) {
            e.printStackTrace();
        }
        cr.accept(cp, 0);
    }
}
