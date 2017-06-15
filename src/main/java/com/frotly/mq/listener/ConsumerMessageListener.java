package com.frotly.mq.listener;

import com.cloudzone.cloudmq.api.open.base.Action;
import com.cloudzone.cloudmq.api.open.base.ConsumeContext;
import com.cloudzone.cloudmq.api.open.base.Msg;
import com.cloudzone.cloudmq.api.open.base.MsgListener;

/**
 * Created by songyigui on 2017/6/12.
 */
public class ConsumerMessageListener implements MsgListener {
    @Override
    public Action consume(Msg msg, ConsumeContext context) {

        System.out.println("Receive:" + new String(msg.getBody()));
        System.out.println(msg.toString());

        try {
            Thread.sleep(2000);
            return Action.CommitMessage;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Action.ReconsumeLater;
        }
    }
}
