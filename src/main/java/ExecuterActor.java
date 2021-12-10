import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExecuterActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(Test.class, test -> sender().tell(execute(test), self()))
                .build();
    }

    public String execute(Test test) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new
                ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(test.getJsScript());
        Invocable invocable = (Invocable) engine;
        System.out.println(test.getFunctionName());
        System.out.println(test.getJsScript());
        System.out.println(test.getParams());
        System.out.println(invocable.invokeFunction(test.getFunctionName(), test.getParams()).toString());
        return invocable.invokeFunction(test.getFunctionName(), test.getParams().toArray()).toString();
    }
}
