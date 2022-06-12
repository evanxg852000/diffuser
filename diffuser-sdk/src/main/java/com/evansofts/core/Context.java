package com.evansofts.core;

import akka.actor.ActorRef;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Context {
    private ActorRef self;
    private List<ActorRef> listeners;

    public Context(ActorRef self) {
        this.self = self;
        this.listeners = new ArrayList<>();
    }

    public void addListener(ActorRef listener) {
        this.listeners.add(listener);
    }

    public void broadcast(Object message) {
        for(ActorRef listener : this.listeners) {
            listener.tell(message, this.self);
        }
    }

    public void schedule(Object message, int delay) {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        broadcast(message);
                    }
                },
                delay
        );
    }

    public void broadcastSelf(Object message) {
        this.self.tell(message, this.self);
    }



    public void scheduleSelf(Object message,int delay) {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        broadcastSelf(message);
                    }
                },
                delay
        );
    }
}
