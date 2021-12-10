import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class RouterActor extends AbstractActor {

    public final ActorRef results;


    public RouterActor() {
        results = getContext().actorOf(Props.create(ResultingActor.class));
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
        .matchAny(m -> System.out.println(m))
                .build();
    }
}
