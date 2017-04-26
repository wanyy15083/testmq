package com.frotly.mq.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;

/**
 * Created by songyigui on 2017/4/12.
 */
public class SpringProducer {
    protected Logger log = org.slf4j.LoggerFactory.getLogger(SpringProducer.class);

    private String producerGroup;

    private String namesrvAddr;

    private String instanceName;

    private DefaultMQProducer producer;

    public DefaultMQProducer getProducer() {
        return producer;
    }

    public SpringProducer(String producerGroup, String namesrvAddr, String instanceName) {
        this.producerGroup = producerGroup;
        this.namesrvAddr = namesrvAddr;
        this.instanceName = instanceName;
    }

    public void init() throws MQClientException {
        log.info("start init DefaultMQProducer...");
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setInstanceName(instanceName);
        producer.start();
        log.info("DefaultMQProducer init success.");
    }

    public void destroy() {
        log.info("start destroy DefaultMQProducer...");
        producer.shutdown();
        log.info("DefaultMQProducer destroy success.");
    }

}
