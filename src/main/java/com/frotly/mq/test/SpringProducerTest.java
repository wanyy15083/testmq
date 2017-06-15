package com.frotly.mq.test;

import com.cloudzone.cloudmq.api.open.base.Msg;
import com.cloudzone.cloudmq.api.open.base.Producer;
import com.cloudzone.cloudmq.api.open.base.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by songyigui on 2017/4/12.
 */
public class SpringProducerTest {
    private static final Logger log = LoggerFactory.getLogger(SpringProducerTest.class);

    public static void main(String[] args) throws Exception {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-producer.xml");
//        final SpringProducer producer = (SpringProducer) ac.getBean("producer");
//        DefaultMQProducer p = producer.getProducer();
//
//        String message = "test messgae"+new Date();
//        Message msg = new Message("testTopic",message.getBytes());
//        log.info(message);
//        p.send(msg, new SendCallback(){
//
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                log.info(sendResult.getSendStatus().name());
//                log.info("onSuccess");
//
//                producer.destroy();
//            }
//
//            @Override
//            public void onException(Throwable e) {
//                log.error("onException");
//            }
//        });

        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-producer.xml");
        Producer producer = (Producer) ac.getBean("cloudProducer");
        for (int i = 0; i < 10; i++) {
            Msg msg = new Msg("o2m-nic-sku-send-200", "sku200", ("sku-test1" + i).getBytes());
            msg.setKey("sku" + i);
            try {
                SendResult result = producer.send(msg);
                System.out.println("200 send success,msgId=" + result.getMsgId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            Msg msg = new Msg("o2m-nic-sku-send-300", "sku300", ("sku-test2" + i).getBytes());
            msg.setKey("sku" + i);
            try {
                SendResult result = producer.send(msg);
                System.out.println("300 send success,msgId=" + result.getMsgId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 10; i++) {
            Msg msg = new Msg("o2m-nic-sku-send-300", "sku200", ("sku-test2" + i).getBytes());
            msg.setKey("sku" + i);
            try {
                SendResult result = producer.send(msg);
                System.out.println("300 send success,msgId=" + result.getMsgId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("producer send message end");
        producer.shutdown();

    }
}
