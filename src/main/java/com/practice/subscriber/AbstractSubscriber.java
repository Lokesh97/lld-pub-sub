package com.practice.subscriber;

import com.practice.entity.Message;
import com.practice.entity.Topic;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractSubscriber {

    protected String id;
    protected AtomicInteger offset;
    protected Topic topic;

    public abstract void consume(Message message) throws InterruptedException;

    public String getId() {
        return id;
    }

    public void setOffset(int offset) {
        this.offset = new AtomicInteger(offset);
    }

    public AtomicInteger getOffset() {
        return this.offset;
    }

    public Topic getTopic() {
        return topic;
    }
}
