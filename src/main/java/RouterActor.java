import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class RouterActor extends AbstractActor {

    public final ActorRef results;


    public RouterActor() {
        results = getContext().actorOf()
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
        .matchAny(m -> System.out.println(m))
                .build();
    }
}
