package com.frotly.mq.test;

import com.cloudzone.cloudmq.api.open.base.Consumer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by songyigui on 2017/4/12.
 */
public class SpringConsumerTest {

    public static void main(String[] args) throws InterruptedException {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-consumer.xml");
//        SpringConsumer consumer = (SpringConsumer) ac.getBean("consumer");
//
//        Thread.sleep(20 * 1000);
//
//        System.out.println("end");
//        consumer.destroy();

        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-consumer.xml");
        Consumer consumer = (Consumer) ac.getBean("cloudConsumer");
        System.out.println("consumer start");

    }
}
