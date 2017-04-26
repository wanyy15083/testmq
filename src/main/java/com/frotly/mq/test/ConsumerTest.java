package com.frotly.mq.test;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.frotly.mq.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by songyigui on 2017/4/12.
 */
public class ConsumerTest {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerTest.class);

    public static void main(String[] args) {
        receiveMsg();
    }


    public static void receiveMsg() {
        //获取消费者
        DefaultMQPushConsumer consumer = Consumer.getDefaultMQPushConsumer();

        try {
            //订阅主体
            consumer.subscribe("TopicTest1", "*");
            //默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    logger.info("currentThreadName:{} and Receive New Message:{}", Thread.currentThread().getName(), msgs);

                    MessageExt msg = msgs.get(0);
                    //执行Topic Test1的消费逻辑
                    if ("TopicTest1".equals(msg.getTopic())) {
                        if (msg.getTags() != null && "TagA".equals(msg.getTags())) {
                            //执行TagA的消费
                            logger.info("MsgBody:{}", new String(msg.getBody()));
                        } else if (msg.getTags() != null && msg.getTags().equals("TagC")) {
                            // 执行TagC的消费
                        } else if (msg.getTags() != null && msg.getTags().equals("TagD")) {
                            // 执行TagD的消费
                        }
                    } else if ("TopicTest2".equals(msg.getTopic())) {
                        // 执行TopicTest2的消费逻辑
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

                }
            });

            //Consumer对象在使用之前必须要调用start初始化，初始化一次即可
            consumer.start();
            logger.info("Cosumer Started.");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
