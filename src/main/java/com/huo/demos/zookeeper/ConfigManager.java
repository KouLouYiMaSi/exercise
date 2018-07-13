package com.huo.demos.zookeeper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.I0Itec.zkclient.ZkClient;

public class ConfigManager {

    private FtpConfig ftpConfig;

    /**
     * 模拟从db加载初始配置
     */
    public void loadConfigFromDB() {
        System.out.println("模拟从数据库载入载入config");
        ftpConfig = new FtpConfig(21, "192.168.1.1", "test", "123456");
    }

    /**
     * 模拟更新DB中的配置
     *
     * @param port
     * @param host
     * @param user
     * @param password
     */
    public void updateFtpConfigToDB(int port, String host, String user, String password) {
        if (ftpConfig == null) {
            ftpConfig = new FtpConfig();
        }
        ftpConfig.setPort(port);
        ftpConfig.setHost(host);
        ftpConfig.setUser(user);
        ftpConfig.setPassword(password);
        System.out.println("模拟写入到数据库");
        // TODO...
    }

    /**
     * 将配置同步到ZK
     */
    public void syncFtpConfigToZk() throws JsonProcessingException {
        ZkClient zk = ZKUtil.getZkClient();
        // 如果ZK中不存在节点，创建一个永久节点
        if (!zk.exists(ZKUtil.FTP_CONFIG_NODE_NAME)) {
            zk.createPersistent(ZKUtil.FTP_CONFIG_NODE_NAME, true);
        }
        // 向节点写入对象
        zk.writeData(ZKUtil.FTP_CONFIG_NODE_NAME, ftpConfig);
        zk.close();
    }

}