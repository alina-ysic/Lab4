import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class ResultingActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .build();
    }
}
