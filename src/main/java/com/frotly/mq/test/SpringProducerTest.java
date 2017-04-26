package com.frotly.mq.test;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.frotly.mq.producer.SpringProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by songyigui on 2017/4/12.
 */
public class SpringProducerTest {
    private static final Logger log = LoggerFactory.getLogger(SpringProducerTest.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-producer.xml");
        final SpringProducer producer = (SpringProducer) ac.getBean("producer");
        DefaultMQProducer p = producer.getProducer();

        String message = "test messgae"+new Date();
        Message msg = new Message("testTopic",message.getBytes());
        log.info(message);
        p.send(msg, new SendCallback(){

            @Override
            public void onSuccess(SendResult sendResult) {
                log.info(sendResult.getSendStatus().name());
                log.info("onSuccess");

                producer.destroy();
            }

            @Override
            public void onException(Throwable e) {
                log.error("onException");
            }
        });

    }
}
