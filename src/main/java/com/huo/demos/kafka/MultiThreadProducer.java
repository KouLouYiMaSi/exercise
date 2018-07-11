package com.huo.demos.kafka;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * 多线程利用同一个线程安全的KafkaProducer进行生产
 *
 * @author bjhuoqingyuan
 *
 */
public class MultiThreadProducer implements Runnable {
    private KafkaProducer<String, String> producer = null;
    private ProducerRecord<String, String> record = null;

    public MultiThreadProducer(KafkaProducer<String, String> producer, ProducerRecord<String, String> record) {
        super();
        this.producer = producer;
        this.record = record;
    }

    @Override
    public void run() {
        producer.send(record, new Callback() {

            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (null != exception) {
                    System.out.println("Send Message Exception." + exception);
                }
                if (null != metadata) {
                    System.out.println("偏移量：" + metadata.offset() + "分区：" + metadata.partition());
                }
            }
        });
    }

    public static void main(String[] args) {
        ProducerRecord<String, String> record = null;
        StockQuotationInfo quotationInfo = null;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < 10; i++) {
                quotationInfo = Producer.createQuotationInfo();
                record = new ProducerRecord<String, String>(Producer.TOPIC, null, quotationInfo.getTradeTime(),
                        quotationInfo.getStockCode(), quotationInfo.toString());
                executor.submit(new MultiThreadProducer(Producer.producer, record));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            //这边需要注意，多线程执行会出现异步问题，异步提交之后直接返回导致producer还没执行就已经关闭了
//            Producer.producer.close();
        }
    }
}
