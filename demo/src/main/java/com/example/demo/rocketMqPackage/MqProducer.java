package com.example.demo.rocketMqPackage;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class MqProducer {
    public static void main(String[] args) throws MQClientException {
        //事务消息
        TransactionMQProducer producer = new TransactionMQProducer("xoxogp1");
        //普通生产者消息
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("ASD");

        producer.setNamespace("192.168.150.113:9876");

        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                return null;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                return null;
            }
        });

        //启动生产者
        producer.start();
        TransactionSendResult sendResult  = producer.sendMessageInTransaction(new Message("xxoo003", "这是一个事务消息".getBytes()), null);
        System.out.println("sendResult ："+sendResult );

        System.out.println("已经停机");
    }
}
