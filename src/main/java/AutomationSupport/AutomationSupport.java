package AutomationSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AutomationFramework.Keywords;
import AutomationFramework.TestSuite;
import AutomationJson.AutomationJson;
import CSV.CSVWriter;

public class AutomationSupport {
    public static void main(String[] args) throws IOException {
        String filePath = "./sample.csv";
        String outputFilePath = "C:\\Users\\VVV\\o2o-api-automation\\api\\testcases\\test.csv";
        String apiParentFilePath = "C:\\Users\\VVV\\o2o-api-automation\\api";

        TestSuite testSuite = new TestSuite();
        Keywords keywords = new Keywords();
        testSuite.generatingTestSuiteFromInputFile(filePath, apiParentFilePath);
        keywords.generatingKeywordsFileFromInputFile(filePath,apiParentFilePath);
//        testSuite.generateTestSuiteFileContent(filePath, outputFilePath);
    }
}
