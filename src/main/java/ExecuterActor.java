import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class ExecuterActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(Test.class, )
    }

    public void execute(Test test) {
        
    }
}
