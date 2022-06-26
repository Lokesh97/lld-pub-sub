package com.practice.handler;

import com.practice.entity.Message;
import com.practice.entity.Topic;
import com.practice.subscriber.AbstractSubscriber;
import com.practice.subscriber.SubscriberWorker;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
    private Topic topic;
    private Map<String, SubscriberWorker> subscriberWorkerMap;

    public TopicHandler(Topic topic) {
        this.topic = topic;
        this.subscriberWorkerMap = new HashMap<>();
    }

    public void publish(Message message){
        this.topic.addMessage(message);
        for (AbstractSubscriber sub:
                topic.getSubscribers()) {
            if(subscriberWorkerMap.containsKey(sub.getId())){
                subscriberWorkerMap.get(sub.getId()).wakeUpIfNeeded();
            } else {
                SubscriberWorker subworker = new SubscriberWorker(sub);
                Thread subWorkerThread = new Thread(subworker);
                subscriberWorkerMap.put(sub.getId(), subworker);
                subWorkerThread.start();
            }
        }
    }

    public Topic getTopic() {
        return topic;
    }
}
