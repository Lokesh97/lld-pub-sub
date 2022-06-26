package com.practice.entity;

import com.practice.subscriber.AbstractSubscriber;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String id;
    private List<Message> messages;
    private List<AbstractSubscriber> subscribers;

    public Topic(String id) {
        this.id = id;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addMessage(Message message) {
       this.messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addSubscriber(AbstractSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public List<AbstractSubscriber> getSubscribers() {
        return subscribers;
    }
}
