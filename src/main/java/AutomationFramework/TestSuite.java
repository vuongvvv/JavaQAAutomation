package AutomationFramework;

import AutomationFolder.AutomationFolder;
import AutomationJson.AutomationJson;
import CSV.CSVReader;
import CSV.CSVWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSuite {

    private static final String testSuiteTestCase = "*** Test Cases ***";
    private static final String testSuiteFilePostFix = "_test.robot";
    private static final String testSuiteFolderOnFramework = "testcases";

    public List<String> generateTestCaseId(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList;
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.ID.toString());
        return returnList;
    }

    public List<String> generateTestCaseDocumentation(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList;
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.TEST_CASE_DOCUMENTATION.toString());
        for (int i = 0; i < returnList.size(); i++) {
            returnList.set(i, AutomationFrameworkConstants.SPACES_FORMAT + "[Documentation]" + AutomationFrameworkConstants.SPACES_FORMAT + returnList.get(i));
        }
        return returnList;
    }

    public List<String> generateTestCaseTags(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList;
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.TEST_CASE_TAGS.toString());
        for (int i = 0; i < returnList.size(); i++) {
            String tagsString = returnList.get(i).replace(";", AutomationFrameworkConstants.SPACES_FORMAT);
            returnList.set(i, AutomationFrameworkConstants.SPACES_FORMAT + "[Tags]" + AutomationFrameworkConstants.SPACES_FORMAT + tagsString);
        }
        return returnList;
    }

    public List<String> generateVerificationPoints(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList;
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.JSON_RESPONSE.toString());
        for (int i = 0; i < returnList.size(); i++) {
            returnList.set(i, AutomationFrameworkConstants.SPACES_FORMAT + returnList.get(i) + AutomationFrameworkConstants.SPACES_FORMAT);
        }
        return returnList;
    }

    public List<String> generateResponseCode(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList;
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.RESPONSE_CODE.toString());
        for (int i = 0; i < returnList.size(); i++) {
            returnList.set(i, AutomationFrameworkConstants.SPACES_FORMAT + "Response Correct Code" + AutomationFrameworkConstants.SPACES_FORMAT + returnList.get(i) + AutomationFrameworkConstants.SPACES_FORMAT);
        }
        return returnList;
    }

    public List<String> generateTestCaseStepsFromJson(String inputFilePath, int index) {
        CSVReader csvReader = new CSVReader();
        List<String> jsonStringList;
        jsonStringList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.JSON_RESPONSE.toString());

        AutomationJson automationJson = new AutomationJson();
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> listTestSteps = new ArrayList<>();

        String jsonString = jsonStringList.get(index).replace(";", ",");
        map = automationJson.convertJsonToMap(jsonString);
        automationJson.generateTestStepsFromMap(map).forEach((k) -> listTestSteps.add(k));
        return listTestSteps;
    }

    public List<String> createTestSuiteFiles(String inputFilePath, String parentpath) {
        AutomationFolder automationFolder = new AutomationFolder();
        CSVReader csvReader = new CSVReader();
        List<String> featuresList;
        List<String> subFeaturesList;
        List<String> apiNameList;
        List<String> createdFiles = new ArrayList<String>();

        featuresList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.FEATURE.toString());
        subFeaturesList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.SUB_FEATURE.toString());
        apiNameList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.API_NAME.toString());
        for (int i = 0; i < featuresList.size(); i++) {
            String fullPathFeature = parentpath.concat(AutomationFrameworkConstants.PATH_DELIMITER + featuresList.get(i).toLowerCase());
            String fullPathSubFeature = fullPathFeature.concat(AutomationFrameworkConstants.PATH_DELIMITER + subFeaturesList.get(i));
            String pathToCreateTestSuite = fullPathSubFeature.concat(AutomationFrameworkConstants.PATH_DELIMITER + apiNameList.get(i).toLowerCase() + testSuiteFilePostFix);
            automationFolder.createFolder(fullPathFeature);
            automationFolder.createFolder(fullPathSubFeature);
            automationFolder.createFile(pathToCreateTestSuite);
            createdFiles.add(pathToCreateTestSuite);
        }
        return createdFiles;
    }

    public void generateTestSuiteFileContent(String inputFilePath, List<String> outputFilePath) {
        CSVWriter csvWriter = new CSVWriter();
        List<String> testCaseIdList;
        List<String> testCaseDocumentationList;
        List<String> testCaseTagsList;
        List<String> testCaseResponseCodeList;
        List<String> testCaseSteps;

        testCaseIdList = generateTestCaseId(inputFilePath);
        testCaseDocumentationList = generateTestCaseDocumentation(inputFilePath);
        testCaseTagsList = generateTestCaseTags(inputFilePath);
        testCaseResponseCodeList = generateResponseCode(inputFilePath);

        for (int i = 0; i < testCaseIdList.size(); i++) {
            List<String> appendMessage = new ArrayList<String>();
            appendMessage.add(AutomationFrameworkConstants.NEW_LINE);
            appendMessage.add(testCaseIdList.get(i));
            appendMessage.add(testCaseDocumentationList.get(i));
            appendMessage.add(testCaseTagsList.get(i));
            appendMessage.add(testCaseResponseCodeList.get(i));

            testCaseSteps = generateTestCaseStepsFromJson(inputFilePath, i);
            for (String testStep : testCaseSteps) {
                appendMessage.add(testStep);
            }
            csvWriter.apprendToCsv(outputFilePath.get(i), appendMessage);
        }
    }

    public void generatingTestSuiteFromInputFile(String inputFilePath, String destinationPath) {
        List<String> createdFiles;
        destinationPath = destinationPath.concat(AutomationFrameworkConstants.PATH_DELIMITER + testSuiteFolderOnFramework);
        createdFiles = createTestSuiteFiles(inputFilePath, destinationPath);
        generateTestSuiteFileContent(inputFilePath, createdFiles);
    }
}
