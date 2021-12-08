import akka.actor.AbstractActor;

public class RouterActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        .matchAny(m -> LOG.warn("Unknown message: {}", m))
                .build();
    }
}
