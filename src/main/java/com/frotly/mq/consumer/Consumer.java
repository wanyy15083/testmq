package com.frotly.mq.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * Created by songyigui on 2017/4/12.
 */
public class Consumer {
    private static DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
    private static int initialState = 0;

    private Consumer() {

    }

    public static DefaultMQPushConsumer getDefaultMQPushConsumer() {
        if (consumer == null) {
            consumer = new DefaultMQPushConsumer("ConsumerGroupName");
        }

        if (initialState == 0) {
            consumer.setNamesrvAddr("10.128.31.15:9876");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            initialState = 1;
        }
        return consumer;
    }
}
