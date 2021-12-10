import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PostRequest {
    private String packageId;
    private String jsScript;
    private String functionName;
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

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getPackageId() {
        return packageId;
    }
}
