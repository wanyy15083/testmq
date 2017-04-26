package com.frotly.mq.test;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.frotly.mq.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by songyigui on 2017/4/12.
 */
public class ProducerTest {
    private static final Logger logger = LoggerFactory.getLogger(ProducerTest.class);

    public static void main(String[] args) {
        sendMsg();
    }

    public static void sendMsg() {
        //获取生产者
        DefaultMQProducer producer = Producer.getDefaulMQProducer();
        try {
            for (int i = 0; i < 2000; i++) {
                //topic tag key body
                Message msg = new Message("TopicTest1", "TagA", "OrderID00" + i, ("Hello MetaQ" + i).getBytes());
                SendResult sendResult = producer.send(msg);
                logger.info("sendResult:{}", sendResult);
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }

}
