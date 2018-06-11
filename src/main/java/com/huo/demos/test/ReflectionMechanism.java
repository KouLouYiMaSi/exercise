package com.huo.demos.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionMechanism {
    public static void main(String[] args) throws Exception {
        ClassNeedReflect target = new ClassNeedReflect();
        Class<ClassNeedReflect> clazz  = ClassNeedReflect.class;
        Field field = clazz.getDeclaredField("fieldReflected");
        Constructor<ClassNeedReflect> constructor = clazz.getDeclaredConstructor();
        Method method = clazz.getDeclaredMethod("methodReflected");
        method.invoke(target);
    }
    static class ClassNeedReflect {
        private String fieldReflected;
        private ClassNeedReflect(){
            System.out.println("构造方法");
        }
        public void methodReflected() {
            System.out.println("反射的方法");
        }
    }
}
