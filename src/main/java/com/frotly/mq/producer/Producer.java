package com.frotly.mq.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

/**
 * Created by songyigui on 2017/4/12.
 */
public class Producer {
    private static DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
    private static int initialState = 0;

    private Producer() {

    }

    public static DefaultMQProducer getDefaulMQProducer() {
        if (producer == null) {
            producer = new DefaultMQProducer("ProducerGroupName");
        }

        if (initialState == 0) {
            producer.setNamesrvAddr("10.128.31.15:9876");
            try {
                producer.start();
            } catch (MQClientException e) {
                e.printStackTrace();
                return null;
            }
            initialState = 1;
        }
        return producer;
    }
}
