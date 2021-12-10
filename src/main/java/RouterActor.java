import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;

public class RouterActor extends AbstractActor {

    private final ActorRef results;
    private final Router router;

    public RouterActor() {
        results = getContext().actorOf(Props.create(ResultingActor.class));
        //getContext().watch(results);
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < 5; i++) {
            ActorRef r = getContext().actorOf(Props.create(ExecuterActor.class));
            //getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(PostRequest.class, request -> executeTests(request))
            .match(GetRequest.class, m -> System.out.println(m))
                .build();
    }

    private void executeTests(PostRequest request) {
        for (Test test : request.getTests()) {
            router.route(test, results);
        }
    }
}
