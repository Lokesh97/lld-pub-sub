package com.practice;

import com.practice.entity.Message;
import com.practice.entity.Topic;
import com.practice.handler.TopicHandler;
import com.practice.subscriber.AbstractSubscriber;

import java.util.HashMap;
import java.util.Map;

public class Queue {

    private Map<String, TopicHandler> topicHandlerMap;

    public Queue() {
        this.topicHandlerMap = new HashMap<>();
    }

    public Topic createTopic(String name){
        TopicHandler topicHandler = new TopicHandler(new Topic(name));
        topicHandlerMap.put(name, topicHandler);
        System.out.println("success");
        return topicHandler.getTopic();
    }

    public void subscribe(String topic, AbstractSubscriber subscriber){
        topicHandlerMap.get(topic).getTopic().addSubscriber(subscriber);
        System.out.println("success");
    }

    public void publish(String topic, Message message){
        new Thread(() -> topicHandlerMap.get(topic).publish(message)).start();
        System.out.println("success");
    }

    public void resetOffset(AbstractSubscriber subscriber, int newOffset){
        subscriber.setOffset(newOffset);
    }
}
