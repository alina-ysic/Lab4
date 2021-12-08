import akka.actor.typed.ActorSystem;

public class TestingApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
    }
}
