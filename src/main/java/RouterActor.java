import akka.actor.AbstractActor;

public class RouterActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
        .matchAny(m -> LOG.warn("Unknown message: {}", m))
                .build();
    }
}
