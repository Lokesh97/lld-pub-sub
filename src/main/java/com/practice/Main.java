package com.practice;

import com.practice.entity.Message;
import com.practice.entity.Topic;
import com.practice.subscriber.AbstractSubscriber;
import com.practice.subscriber.SleepingSubscriber;

public class Main {
    public static void main(String[] args) {
        Queue q = new Queue();

        Topic t1 = q.createTopic("t1");
        AbstractSubscriber s1 = new SleepingSubscriber(t1);
        AbstractSubscriber s2 = new SleepingSubscriber(t1);

        q.subscribe(t1.getId(),s1);
        q.subscribe(t1.getId(),s2);

        q.publish(t1.getId(), new Message("Hello"));
        q.publish(t1.getId(), new Message("Bye"));


        q.resetOffset(s1,1);
    }
}
