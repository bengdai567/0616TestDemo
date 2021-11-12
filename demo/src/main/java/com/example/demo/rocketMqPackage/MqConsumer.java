package com.example.demo.rocketMqPackage;


import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class MqConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
//        DefaultLitePullConsumer defaultLitePullConsumer = new DefaultLitePullConsumer();
        consumer.setNamesrvAddr("192.168.150.113:9876");
        //订阅指定的topic和所有队列
        consumer.subscribe("ASD", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt messageExt : msgs) {
                    //获取消息
                    System.out.println(new String(messageExt.getBody()));
                }
                // 默认情况下 这条消息只会被 一个consumer 消费到 点对点
                // message 状态修改
                // ack
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        /**
         * 发送消息的时候网络问题超时第一条准时到达，会发送第二条，这样的话会有两条一样的消息？

         */
        //设置超时消费时间
//        consumer.setConsumeTimeout(consumeTimeout);
        //设置消费模式，即集群消费模式，任意一个被集群消费即可
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.start();
        // 集群 -> 一组consumer
        // 广播

        System.out.println("Consumer 02 start...");

    }
}
