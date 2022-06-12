package com.evansofts.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.evansofts.core.IComponent;

import java.util.HashMap;

public class Universe {
    private HashMap<String, ActorRef> actors;
    private ActorSystem actorSystem;

    public Universe() {
        this.actors = new HashMap<>();
        this.actorSystem = ActorSystem.create("diffuser-universe");
    }

    public void spawnActor(IComponent component) {
        ActorRef actorRef = actorSystem.actorOf(ComponentActor.props(component));
        this.actors.put(component.instanceId(), actorRef);
    }

    public void shutdown() {
        for (ActorRef actorRef : this.actors.values()) {
            this.actorSystem.stop(actorRef);
        }
    }

    public boolean sendMessage(String id, Object message) {
        ActorRef actorRef = this.actors.get(id);
        if (actorRef == null) {
            return false;
        }
        actorRef.tell(message, null);
        return true;
    }

}
