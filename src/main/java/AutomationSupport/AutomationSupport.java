package AutomationSupport;

import CSVReader.CSVReaderWithHeaderAutoDetection;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AutomationSupport {
    public static void main(String[] args) throws IOException {
        CSVReaderWithHeaderAutoDetection csvReaderWithHeaderAutoDetection = new CSVReaderWithHeaderAutoDetection();
        List<String> nameList = Arrays.asList();
        nameList = csvReaderWithHeaderAutoDetection.readCSVFileByHeader("./users-with-header.csv", "Name");
        System.out.println(nameList);
    }
}
