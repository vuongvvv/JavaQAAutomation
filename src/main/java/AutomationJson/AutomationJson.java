package AutomationJson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutomationJson {
    private static final String spacesFormat = "    ";
    private static final String verificationKeywordString = "TO_BE_REPLACED_BY_ASSERTION_KEYWORD";

    public Map<String, Object> convertJsonToMap(String jsonString) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            returnMap = mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    public List<String> generateTestStepsFromMap(Map<String, Object> inputMap) {
        List<String> testSteps = new ArrayList<String>();
        inputMap.forEach((k, v) -> {
            testSteps.add(spacesFormat + verificationKeywordString + spacesFormat + k.toString() + spacesFormat + v.toString());
        });
        return testSteps;
    }
}
