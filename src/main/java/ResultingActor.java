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
                    Map<String, String> packageResult = results.get(getRequest.getPachageId());
                    if (packageResult == null) packageResult = new HashMap<>();
                    sender().tell(packageResult, ActorRef.noSender());
                    System.out.println(sender());
                })
                .match(Test.class, this::saveResult)
                .build();
    }

    private void saveResult(Test test) {
        Map<String, String> packageResults = results.get(test.getPackageId());
        if (packageResults == null) packageResults = new HashMap<>();
        String result = (test.getActualResult().equals(test.getExpectedResult())) ? "ok" : "not ok";
        packageResults.put(test.getTestName(), result);
        results.put(test.getPackageId(), packageResults);
    }
}
