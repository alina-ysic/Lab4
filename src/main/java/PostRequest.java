import java.util.ArrayList;

public class PostRequest {
    @JsonProperty("packageId")
    private String packageId;
    @JsonProperty("jsScript")
    private String jsScript;
    private String functionName;
    ArrayList<Test> tests;

    public ArrayList<Test> getTests() {
        return tests;
    }
}
