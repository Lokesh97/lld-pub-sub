package com.practice.subscriber;

import com.practice.entity.Message;
import com.practice.entity.Topic;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class SleepingSubscriber extends AbstractSubscriber{

    public SleepingSubscriber(Topic topic) {
        this.id = UUID.randomUUID().toString();
        this.offset = new AtomicInteger(0);
        this.topic = topic;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(message.getMsg());
    }
}
