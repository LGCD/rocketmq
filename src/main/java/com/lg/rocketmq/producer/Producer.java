package com.lg.rocketmq.producer;

import lombok.extern.java.Log;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

/**
 * @author ligang
 * @date 2019-02-11
 */
@Log
@Component
public class Producer {

    /**
     * 生产者的组名
     */
    // @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup = "Producer";

    /**
     * NameServer 地址
     */
    // @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr = "10.96.86.12:9876";

    // @PostConstruct
    public void defaultMQProducer() {

        log.info("Producer start ");

        //生产者的组名
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);

        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);

        try {

            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();

            for (int i = 0; i < 100; i++) {

                String messageBody = "我是消息内容:" + i;

                String message = new String(messageBody.getBytes(), "utf-8");

                //构建消息
                Message msg = new Message("PushTopic", "push", "key_" + i, message.getBytes());

                //发送消息
                SendResult result = producer.send(msg);

                System.out
                    .println("  ------   " + i);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }

    }
}
