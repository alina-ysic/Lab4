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

    private final ActorRef resultActor;
    private final Router executerRouter;

    public RouterActor() {
        resultActor = getContext().actorOf(Props.create(ResultingActor.class));
        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ActorRef r = getContext().actorOf(Props.create(ExecuterActor.class));
            routees.add(new ActorRefRoutee(r));
        }
        executerRouter = new Router(new RoundRobinRoutingLogic(), routees);
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(PostRequest.class, postRequest -> executeTests(postRequest))
            .match(GetRequest.class, getRequest -> {
                resultActor.tell(getRequest, sender());
            })
                .build();
    }

    private void executeTests(PostRequest request) {
        for (Test test : request.getTests()) {
            executerRouter.route(test, resultActor);
        }
    }
}
