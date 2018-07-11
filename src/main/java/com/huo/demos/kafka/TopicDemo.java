package com.huo.demos.kafka;

import java.util.Map;
import java.util.Properties;

import org.apache.kafka.common.security.JaasUtils;

import kafka.admin.AdminUtils;
import kafka.admin.BrokerMetadata;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import scala.collection.Seq;

public class TopicDemo {
    public static final String URL = "192.168.99.100:2181";
    public final static String TOPIC_NAME = "stock-info";
    public static final long SESSION_TIMEOUT = 30000;
    public static final long CONNECT_TIMEOUT = 30000;

    /**
     * @param topic
     * @param partition
     * @param replica
     * @param properties
     */
    public static void createTopic() {
        ZkUtils zkUtils = null;
        try {
            // 参数从左到右，zk的IP端口，会话超时时间，连接超时时间，zk安全验证是否开启
            zkUtils = ZkUtils.apply(URL, 30000, 30000, JaasUtils.isZkSecurityEnabled());
            if (!AdminUtils.topicExists(zkUtils, TOPIC_NAME)) {
                AdminUtils.createTopic(zkUtils, TOPIC_NAME, 1, 1, new Properties(), AdminUtils.createTopic$default$6());
            } else {
                // TODO 主题存在
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

    /**
     * 保留上次properties的方法修改主题级别配置
     *
     * @param topic
     * @param properties
     */
    public static void modifyTopicConfig(String topic, Properties properties) {
        ZkUtils zkUtils = null;
        try {
            // 参数从左到右，zk的IP端口，会话超时时间，连接超时时间，zk安全验证是否开启
            zkUtils = ZkUtils.apply(URL, 30000, 30000, JaasUtils.isZkSecurityEnabled());
            Properties curProp = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), topic);
            curProp.putAll(properties);
            AdminUtils.changeTopicConfig(zkUtils, topic, curProp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

    public static void modifyPartitions() {
        ZkUtils zkUtils = null;
        try {
            // 参数从左到右，zk的IP端口，会话超时时间，连接超时时间，zk安全验证是否开启
            zkUtils = ZkUtils.apply(URL, 30000, 30000, JaasUtils.isZkSecurityEnabled());
            Seq<BrokerMetadata> brokerMeta = AdminUtils.getBrokerMetadatas(zkUtils,
                    AdminUtils.getBrokerMetadatas$default$2(), AdminUtils.getBrokerMetadatas$default$3());
            scala.collection.Map<Object, Seq<Object>> replicaAssign = AdminUtils.assignReplicasToBrokers(brokerMeta, 2,
                    1, AdminUtils.assignReplicasToBrokers$default$4(), AdminUtils.assignReplicasToBrokers$default$5());
            AdminUtils.createOrUpdateTopicPartitionAssignmentPathInZK(zkUtils, TOPIC_NAME, replicaAssign, null, true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

    /**
     * 删除主题
     * @param topic
     */
    public static void deleteTopic(String topic) {
        ZkUtils zkUtils = null;
        try {
            // 参数从左到右，zk的IP端口，会话超时时间，连接超时时间，zk安全验证是否开启
            zkUtils = ZkUtils.apply(URL, 30000, 30000, JaasUtils.isZkSecurityEnabled());
            AdminUtils.deleteTopic(zkUtils, topic);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

    public static void main(String[] args) {
        createTopic();
        modifyPartitions();
    }
}
