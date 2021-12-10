import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Test {
    private String expectedResult;
    private String testName;
    private ArrayList<Object> params;

    public Test(@JsonProperty("expectedResult") String expectedResult,
                       @JsonProperty("testName") String testName,
                       @JsonProperty("params") ArrayList<Object> params) {
        this.expectedResult = expectedResult;
        this.testName = testName;
        this.params = params;
    }
}
