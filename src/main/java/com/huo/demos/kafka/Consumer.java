package com.huo.demos.kafka;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * 创建消费者，订阅主题，消费者偏移量管理，消费者多线程实现
 *
 * @author bjhuoqingyuan
 *
 */
public class Consumer {
    public static final int MSG_SIZE = 100;
    public static final String TOPIC = "stock-info";
    public static final String SERVER = "192.168.99.100:9092";
    public static KafkaConsumer<String, String> consumer = null;
    static {
        Properties configs = initConfig();
        consumer = new KafkaConsumer<String, String>(configs);
    }

    /**
     * 首先初始化配置,默认每隔1秒提交一次偏移量
     *
     * @return
     */
    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", SERVER);
        properties.put("group.id", "test");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        return properties;
    }

    // --------------------------------------以上完成Consumer的配置和初始化工作--------------------------------------//
    /**
     * 按照主题订阅
     */
    public static void subscribeTopic() {
        consumer.subscribe(Arrays.asList("stock-info"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                consumer.commitSync(); // 提交偏移量
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                long commitedOffset = -1;
                for (TopicPartition topicPartition : partitions) {
                    // 获取分区已经消费的偏移量
                    commitedOffset = consumer.committed(topicPartition).offset();
                    // 重置偏移量到上一次提交的偏移量下一个位置处开始消费
                    consumer.seek(topicPartition, commitedOffset + 1);
                }
            }
        });
    }

    /**
     * 按照主题和分区订阅
     *
     * @param i
     */
    public static void subscribePartion() {
        consumer.assign(Arrays.asList(new TopicPartition("stock-info", 1)));
    }

    /**
     * 获取指定分区的偏移量
     *
     * @param partition
     * @return
     */
    public static OffsetAndMetadata getOffsetOfPartition(TopicPartition partition) {
        return consumer.committed(partition);
    }

    /**
     * 获取下一次拉取的位置偏移量
     *
     * @param partition
     * @return
     */
    public static long getNextPullPosition(TopicPartition partition) {
        return consumer.position(partition);
    }

    /**
     * 重置消费的起始位置到offset位置
     *
     * @param partition
     * @param offset
     */
    public static void resetBeginPosition(TopicPartition partition, long offset) {
        consumer.seek(partition, offset);
    }

    public static void main(String[] args) {
        consumer.subscribe(Arrays.asList(TOPIC));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord record : records) {
                    System.out.println("partition=" + record.partition() + " offset=" + record.offset() + " key="
                            + record.key() + " value=" + record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

}
