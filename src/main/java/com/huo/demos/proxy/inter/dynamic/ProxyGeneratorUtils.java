package com.huo.demos.proxy.inter.dynamic;

import java.io.FileOutputStream;
import java.io.IOException;

import com.huo.demos.proxy.inter.SMCompany;

public class ProxyGeneratorUtils {

    /**
     * 把代理类的字节码写到硬盘上
     * @param path 保存路径
     */
    public static void writeProxyClassToHardDisk(String path, Class<?>[] intfs) {
        byte[] classFile = sun.misc.ProxyGenerator.generateProxyClass("$SuperStarProxy",intfs);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}