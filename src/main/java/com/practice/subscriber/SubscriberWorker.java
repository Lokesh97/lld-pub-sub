package com.practice.subscriber;

public class SubscriberWorker implements Runnable{

    private AbstractSubscriber subscriber;

    public SubscriberWorker(AbstractSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void wakeUpIfNeeded(){
        synchronized (subscriber) {
            notify();
        }
    }

    public void consumeMessages() throws InterruptedException {
        synchronized (subscriber) {
            while (true) {
                int offset = subscriber.getOffset().get();
                while (offset >= subscriber.getTopic().getMessages().size()) {
                    subscriber.wait();
                }

                subscriber.consume(subscriber.getTopic().getMessages().get(offset));
                this.subscriber.getOffset().compareAndSet(offset, offset + 1);
            }
        }
    }

    @Override
    public void run() {
        try {
            this.consumeMessages();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
