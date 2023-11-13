package com.demorabbitmq.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.queue)
    public void listener(CustomeMessage message) {
        try {
            Thread.sleep(300L);
            System.out.println("First Listener -- " + message.getMessage() + "--Time--" + message.getMessageDate());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("Get Message -- " + message.getMessage() + "--Time--" + message.getMessageDate());
    }

    @RabbitListener(queues = MQConfig.queue2)
    public void listenerTwo(CustomeMessage message) {
        try {
            Thread.sleep(300L);
            System.out.println("Second Listener -- " + message.getMessage() + "--Time--" + message.getMessageDate());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("Get Message for Second Listener ---- " + customeMessage);
    }
}
