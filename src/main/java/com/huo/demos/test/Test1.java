package com.huo.demos.test;

import javax.validation.constraints.Null;

public class Test1 {
    public static void main(String[] args) {
        String[] sts = {"1","2","3","4"};
        System.out.println(String.join(",", sts));
    }

    public static int method1() {
        Integer k = 1;
        String ktr= k.toString();
        String ktr2 = String.valueOf(k);
        try {
            return k;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return k;
        }
    }

    public static int testBasic() {
        int i = 1;
        i = i / 0;
        try {
            i++;
            i = 1 / 0;
            System.out.println("try block, i = " + i);
            return i;
        } catch (Exception e) {
            i++;
            System.out.println("catch block i = " + i);
            return i;
        } finally {
            i = 10;
            System.out.println("finally block i = " + i);
        }
    }
}
