package com.lg.rocketmq.mqTest;

import com.lg.rocketmq.cosumer.Consumer;
import com.lg.rocketmq.producer.Producer;
import org.junit.Test;

/**
 * @author ligang
 * @date 2019-02-11
 */
public class Mq {

    Consumer consumer = new Consumer();
    Producer producer = new Producer();

    @Test
    public void testMq() {
        consumer.defaultMQPushConsumer();
        producer.defaultMQProducer();
    }

    @Test
    public void testProducer() {
        producer.defaultMQProducer();
    }

    @Test
    public void testConsumer() {
        consumer.defaultMQPushConsumer();
    }


}