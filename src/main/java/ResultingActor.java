import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class ResultingActor extends AbstractActor {

    private final Map<String, Map<String, String>> results = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(GetRequest.class, m -> System.out.println(m))
                .match(Test.class, sa)
                .build();
    }

    private saveResult(Test test) {

    }
}
