package com.evansofts.actors;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import com.evansofts.core.Context;
import com.evansofts.core.IComponent;

public class ComponentActor extends UntypedAbstractActor {
    private IComponent component;
    private Context context;

    private ComponentActor(IComponent component) {
        this.component = component;
        this.context = new Context(this.self());
    }

    public static Props props(IComponent component) {
        return Props.create(ComponentActor.class, () -> new ComponentActor(component));
    }

    void addListener(ActorRef listener) {
        this.context.addListener(listener);
    }

    public void broadcast(Object message) {
        this.context.broadcast(message);
    }

    public void broadcastSelf(Object message) {
        this.context.broadcastSelf(message);
    }

    public void scheduleSelf(String message, int delay) {
        this.context.scheduleSelf(message, delay);
    }

    public String instanceId() {
        return this.component.instanceId();
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        this.component.onReceive(this.context, message);
    }

}
