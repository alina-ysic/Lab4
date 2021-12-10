import akka.actor.dsl.Inbox;

public class GetRequest {

    private final String pachageId;

    public GetRequest(String pachageId) {
        this.pachageId = pachageId;
    }

    public String getPachageId() {
        return pachageId;
    }
}
