import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Test {
    private String expectedResult;
    private String testName;
    private ArrayList<Object> params;

    private String packageId;
    private String jsScript;
    private String functionName;

    public Test(@JsonProperty("expectedResult") String expectedResult,
                       @JsonProperty("testName") String testName,
                       @JsonProperty("params") ArrayList<Object> params) {
        this.expectedResult = expectedResult;
        this.testName = testName;
        this.params = params;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public void setJsScript(String jsScript) {
        this.jsScript = jsScript;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
