import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PostRequest {
    ArrayList<Test> tests;

    public PostRequest(@JsonProperty("packageId") String packageId,
                       @JsonProperty("jsScript") String jsScript,
                       @JsonProperty("functionName") String functionName,
                       @JsonProperty("tests") ArrayList<Test> tests) {
        this.tests = tests;

        for (Test test : this.tests) {
            test.setFunctionName(functionName);
            test.setJsScript(jsScript);
            test.setPackageId(packageId);
        }
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
