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
                .match(GetRequest.class, getRequest -> sender().tell())
                .match(Test.class, this::saveResult)
                .build();
    }

    private void saveResult(Test test) {

    }
}
