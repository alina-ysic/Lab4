import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;


import java.io.IOException;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class TestingApp {

    private final ActorRef routerActor;

    public TestingApp(ActorRef routerActor) {
        this.routerActor = routerActor;
    }

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("test");
        ActorRef routerActor = system.actorOf(Props.create(RouterActor.class));

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        TestingApp instance = new TestingApp(routerActor);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                instance.createRoute(system).flow(system, materializer);

        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );

        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());


    }

    public Route createRoute(ActorSystem actorSystem) {
        return route(
                path("test-app", () ->
                        route(
                                get(() -> parameter("packageId", (packageId) -> {
                                    Future<Object> result = Patterns.ask(routerActor,
                                            new GetRequest(packageId), 5000);
                                    return completeOKWithFuture(result, Jackson.marshaller());
                                })),
                                post(() -> { // запрос на запуск теста
                                    entity(Jackson.unmarshaller(PostRequest.class), msg -> {
                                        testPackageActor.tell(msg, ActorRef.noSender());
                                        return complete("Test started!");
                                    System.out.println("post");
                                    return null;
                                })
                        )
                )
        );
    }
}
