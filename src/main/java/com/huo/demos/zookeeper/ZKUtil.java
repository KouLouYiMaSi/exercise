package com.huo.demos.zookeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * 获取ZkClient工具
 *
 * @author bjhuoqingyuan
 *
 */
public class ZKUtil {

    public static final String FTP_CONFIG_NODE_NAME = "/config/ftp";

    public static ZkClient getZkClient() {
        return new ZkClient("192.168.99.104:2183,192.168.99.104:2182,192.168.99.104:2181");
    }

}