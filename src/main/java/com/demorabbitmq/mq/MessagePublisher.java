package com.demorabbitmq.mq;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@RestController
public class MessagePublisher {
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RabbitTemplate template2;
    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomeMessage message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.exchange, MQConfig.routingKey, message);
        long startTime = System.currentTimeMillis();

        Instant instant = Instant.ofEpochMilli(startTime);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDateTime = dateTime.format(formatter);
        System.out.println("First Publisher--- ID-- 1 " + "------ Time ---" + formattedDateTime);
        for(int i = 1; i <= 100; i++) {
            startTime = System.currentTimeMillis();
            CustomeMessage message1 = new CustomeMessage();
            message1.setMessageId(UUID.randomUUID().toString());
            message1.setMessageDate(new Date());
            String s = String.valueOf(i);
            message1.setMessage(s);
            try {
                Thread.sleep(10L);
                template.convertAndSend(MQConfig.exchange, MQConfig.routingKey, message1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            template.convertAndSend(MQConfig.exchange, MQConfig.routingKey, message1);

            if(i % 20 == 0 ) {
                instant = Instant.ofEpochMilli(startTime);
                dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                formattedDateTime = dateTime.format(formatter);
                System.out.println("First Publisher,  Id -- " + i + "-- Time " + formattedDateTime);

            }
        }

        return "Message Published";
    }

    @PostMapping("/publishTwo")
    public String publishMessageTwo(@RequestBody CustomeMessage message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template2.convertAndSend(MQConfig.exchange2, MQConfig.routingKey2, message);
        long startTime = System.currentTimeMillis();

        Instant instant = Instant.ofEpochMilli(startTime);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDateTime = dateTime.format(formatter);
        System.out.println("Second Publisher --- ID-- 1 " + "------ Time ---" + formattedDateTime);
        for(int i = 1; i <= 100; i++) {
            startTime = System.currentTimeMillis();
            CustomeMessage message1 = new CustomeMessage();
            message1.setMessageId(UUID.randomUUID().toString());
            message1.setMessageDate(new Date());
            String s = String.valueOf(i);
            message1.setMessage(s);
            try {
                Thread.sleep(10L);
                template2.convertAndSend(MQConfig.exchange2, MQConfig.routingKey2, message1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            template.convertAndSend(MQConfig.exchange, MQConfig.routingKey, message1);

            if(i % 20 == 0 ) {
                instant = Instant.ofEpochMilli(startTime);
                dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                formattedDateTime = dateTime.format(formatter);
                System.out.println("Second Publisher, Id -- " + i + "-- Time " + formattedDateTime);
            }
        }

        return "Message Published";
    }
}
