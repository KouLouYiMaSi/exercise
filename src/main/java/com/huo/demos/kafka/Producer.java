package com.huo.demos.kafka;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer {
    public static final int MSG_SIZE = 100;
    public static final String TOPIC = "stock-info";
    public static final String SERVER = "192.168.99.100:9092";
    public static KafkaProducer<String, String> producer = null;
    static {
        Properties configs = initConfig();
        producer = new KafkaProducer<String, String>(configs);
    }

    /**
     * 首先初始化配置
     *
     * @return
     */
    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    /**
     * 生成股票信息
     *
     * @return
     */
    public static StockQuotationInfo createQuotationInfo() {
        StockQuotationInfo quotationInfo = new StockQuotationInfo();
        Random r = new Random();
        Integer stockCode = 600100 + r.nextInt(10);
        float random = (float) Math.random();
        if (random / 2 < 0.5) {
            random = -random;
        }
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        quotationInfo.setCurrentPrice(Float.valueOf(decimalFormat.format(11 + random)));
        quotationInfo.setPreClosePrice(11.80f);
        quotationInfo.setOpenPrice(11.50f);
        quotationInfo.setLowPrice(10.50f);
        quotationInfo.setHighPrice(12.5f);
        quotationInfo.setStockCode(stockCode.toString());
        quotationInfo.setTradeTime(System.currentTimeMillis());
        quotationInfo.setStockName("股票-" + stockCode);
        return quotationInfo;
    }

    public static void main(String[] args) {
        ProducerRecord<String, String> record = null;
        StockQuotationInfo quotationInfo = null;
        try {
            int num = 0;
            for (int i = 0; i < MSG_SIZE; i++) {
                quotationInfo = createQuotationInfo();
                record = new ProducerRecord<String, String>(TOPIC, null, quotationInfo.getTradeTime(),
                        quotationInfo.getStockCode(), quotationInfo.toString());
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
                if (num++ % 10 == 0) {
                    Thread.sleep(2000L);
                }
            }
        } catch (Exception e) {
            System.out.println("Send Message Exception:" + e);
        } finally {
            producer.close();
        }
    }
}
