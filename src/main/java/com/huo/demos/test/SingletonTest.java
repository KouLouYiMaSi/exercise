package com.huo.demos.test;

import java.lang.reflect.Constructor;

public class SingletonTest {
    // 私有构造方法
    private SingletonTest() {
        System.out.println("无参数---构造----");
    }

    // 私有构造方法
    private SingletonTest(String a) {
        System.out.println("有参数---构造----参数值：" + a);
    }

    // 定义私有类型的变量
    private static volatile SingletonTest instance;

    // 定义一个静态共有方法
    public static SingletonTest getInstance() {
        if (instance == null) {
            synchronized (SingletonTest.class) {
                if (instance == null) {
                    return new SingletonTest();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        Class clazz = SingletonTest.class;
        /* 以下调用无参的、私有构造函数 */
        Constructor c0 = clazz.getDeclaredConstructor();
        c0.setAccessible(true);
        SingletonTest po = (SingletonTest) c0.newInstance();
        System.out.println("无参构造函数\t" + po);
        /* 以下调用带参的、私有构造函数 */
        Constructor c1 = clazz.getDeclaredConstructor(new Class[] { String.class });
        c1.setAccessible(true);
        SingletonTest p1 = (SingletonTest) c1.newInstance(new Object[] { "我是参数值" });
        System.out.println("有参的构造函数\t" + p1);
    }
}