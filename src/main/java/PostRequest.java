import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PostRequest {
    private String packageId;
    private String jsScript;
    private String functionName;
    ArrayList<Test> tests;

    public PostRequest(String packageId,
                       String jsScript,
                       String functionName,
                       ArrayList<Test> tests) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
