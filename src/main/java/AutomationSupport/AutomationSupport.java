package AutomationSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AutomationFramework.TestSuite;
import AutomationJson.AutomationJson;
import CSV.CSVWriter;

public class AutomationSupport {
    public static void main(String[] args) throws IOException {
        String filePath = "./sample.csv";
        String outputFilePath = "./test.csv";
//        CSVReader csvReader = new CSVReader();
//        List<String> nameList = Arrays.asList();
//        nameList = csvReader.readCSVFileByHeader("./users-with-header.csv", "Name");
//        System.out.println(nameList);

//        CSVWriter csvWriter = new CSVWriter();
//        csvWriter.writeToCsv("./sample.csv", "VVV");
//        String[] messages = new String[]{"VVV1", "VVV2"};
//        csvWriter.apprendToCsv("./sample.csv", messages);


        TestSuite testSuite = new TestSuite();
        testSuite.generateTestSuiteFile(filePath, outputFilePath);
    }
}
