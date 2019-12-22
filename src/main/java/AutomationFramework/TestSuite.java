package AutomationFramework;

import AutomationJson.AutomationJson;
import CSV.CSVReader;
import CSV.CSVWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSuite {
    private static final String spacesFormat = "    ";
    private static final String testSuiteTestCase = "*** Test Cases ***";

    public List<String> generateTestCaseId(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList = new ArrayList<>();
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.ID.toString());
        return returnList;
    }

    public List<String> generateTestCaseDocumentation(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList = new ArrayList<>();
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.TEST_CASE_DOCUMENTATION.toString());
        for (int i = 0; i < returnList.size(); i++) {
            returnList.set(i, spacesFormat + "Documentation" + spacesFormat + returnList.get(i));
        }
        return returnList;
    }

    public List<String> generateTestCaseTags(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList = new ArrayList<>();
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.TEST_CASE_TAGS.toString());
        for (int i = 0; i < returnList.size(); i++) {
            String tagsString = returnList.get(i).replace(" ", spacesFormat);
            returnList.set(i, spacesFormat + "[Tags]" + spacesFormat + tagsString);
        }
        return returnList;
    }

    public List<String> generateVerificationPoints(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList = new ArrayList<>();
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.JSON_RESPONSE.toString());
        for (int i = 0; i < returnList.size(); i++) {
            returnList.set(i, spacesFormat + returnList.get(i) + spacesFormat);
        }
        return returnList;
    }

    public List<String> generateResponseCode(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList = new ArrayList<>();
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.RESPONSE_CODE.toString());
        for (int i = 0; i < returnList.size(); i++) {
            returnList.set(i, spacesFormat + "Response Correct Code" + spacesFormat + returnList.get(i) + spacesFormat);
        }
        return returnList;
    }

    public List<String> generateTestCaseStepsFromJson(String inputFilePath, int index) {
        CSVReader csvReader = new CSVReader();
        List<String> jsonStringList = new ArrayList<>();
        jsonStringList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.JSON_RESPONSE.toString());

        AutomationJson automationJson = new AutomationJson();
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> listTestSteps = new ArrayList<>();

        String jsonString = jsonStringList.get(index).replace(";", ",");
        map = automationJson.convertJsonToMap(jsonString);
        automationJson.generateTestStepsFromMap(map).forEach((k) -> listTestSteps.add(k));
        return listTestSteps;
    }

    public void generateTestSuiteFile(String inputFilePath, String outputFilePath) {
        CSVWriter csvWriter = new CSVWriter();
        List<String> testCaseIdList = new ArrayList<>();
        List<String> testCaseDocumentationList = new ArrayList<>();
        List<String> testCaseTagsList = new ArrayList<>();
        List<String> testCaseResponseCodeList = new ArrayList<>();
        List<String> testCaseSteps = new ArrayList<>();

        testCaseIdList = generateTestCaseId(inputFilePath);
        testCaseDocumentationList = generateTestCaseDocumentation(inputFilePath);
        testCaseTagsList = generateTestCaseTags(inputFilePath);
        testCaseResponseCodeList = generateResponseCode(inputFilePath);


        for (int i = 0; i < testCaseIdList.size(); i++) {
            List<String> appendMessage = new ArrayList<String>();
            appendMessage.add(testCaseIdList.get(i));
            appendMessage.add(testCaseDocumentationList.get(i));
            appendMessage.add(testCaseTagsList.get(i));
            appendMessage.add(testCaseResponseCodeList.get(i));

            testCaseSteps = generateTestCaseStepsFromJson(inputFilePath, i);
            System.out.println(testCaseSteps);
            for (String testStep : testCaseSteps) {
                appendMessage.add(testStep);
            }
            csvWriter.apprendToCsv(outputFilePath, appendMessage);
        }
    }
}
