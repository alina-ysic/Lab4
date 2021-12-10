import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PostRequest {
    @JsonProperty("packageId")
    private String packageId;
    @JsonProperty("jsScript")
    private String jsScript;
    @JsonProperty("functionName")
    private String functionName;
    @JsonProperty("tests")
    ArrayList<Test> tests;

    public PostRequest(@JsonProperty("packageId") String packageId,
                       @JsonProperty("jsScript") String jsScript,
                       @JsonProperty("functionName") String functionName,
                       @JsonProperty("tests") ArrayList<Test> tests) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
