import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class ResultingActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(GetRequest.class, m -> System.out.println(m))
                .match(Test.class, m -> System.out.println(m))
                .build();
    }
}
