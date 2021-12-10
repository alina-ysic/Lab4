import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class ResultingActor extends AbstractActor {

    private final Map<String, Map<String, String>> results = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(GetRequest.class, getRequest -> {
                    System.out.println(results.get(getRequest.getPachageId()));
                    sender().tell(results.get(getRequest.getPachageId()), ActorRef.noSender());
                })
                .match(Test.class, this::saveResult)
                .build();
    }

    private void saveResult(Test test) {
        Map<String, String> packageResults = results.get(test.getPackageId());
        if (packageResults == null) packageResults = new HashMap<>();
        packageResults.put(test.getActualResult(), test.getExpectedResult());
        results.put(test.getPackageId(), packageResults);
        System.out.println(test.getPackageId());
        System.out.println(results.get(test.getPackageId()));
    }
}
