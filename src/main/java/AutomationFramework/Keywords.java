package AutomationFramework;

import AutomationFolder.AutomationFolder;
import CSV.CSVReader;
import CSV.CSVWriter;
import org.apache.commons.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class Keywords {
    private static final String keywordsPostfix = "_keywords.robot";
    private static final String keywordsFolderOnFramework = "keywords";

    public List<String> createKeywordsFiles(String inputFilePath, String parentpath) {
        AutomationFolder automationFolder = new AutomationFolder();
        CSVReader csvReader = new CSVReader();
        List<String> featuresList;
        List<String> subFeaturesList;
        List<String> createdFiles = new ArrayList<String>();
        featuresList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.FEATURE.toString());
        subFeaturesList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.SUB_FEATURE.toString());
        for (int i = 0; i < featuresList.size(); i++) {
            String fullPathFeature = parentpath.concat(AutomationFrameworkConstants.PATH_DELIMITER + featuresList.get(i).toLowerCase());
            String fullPathSubFeatureKeywordsFile = fullPathFeature.concat(AutomationFrameworkConstants.PATH_DELIMITER + subFeaturesList.get(i).toLowerCase() + keywordsPostfix);
            automationFolder.createFolder(fullPathFeature);
            automationFolder.createFile(fullPathSubFeatureKeywordsFile);
            createdFiles.add(fullPathSubFeatureKeywordsFile);
        }
        return createdFiles;
    }

    public List<String> generateKeywords(String inputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String> returnList;
        returnList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.API_NAME.toString());
        for (int i = 0; i < returnList.size(); i++) {
            returnList.set(i, Utils.formatKeywordString(returnList.get(i)));
        }
        return returnList;
    }

    public void generateKeywordsFileContent(String inputFilePath, List<String> outputFilePath) {
        CSVWriter csvWriter = new CSVWriter();
        List<String> keywordsList;

        keywordsList = generateKeywords(inputFilePath);
        for (int i = 0; i < keywordsList.size(); i++) {
            List<String> appendMessage = new ArrayList<String>();
            appendMessage.add(AutomationFrameworkConstants.NEW_LINE);
            appendMessage.add(keywordsList.get(i));
            csvWriter.apprendToCsv(outputFilePath.get(i), appendMessage);
        }
    }

    public void generatingKeywordsFileFromInputFile(String inputFilePath, String destinationPath) {
        List<String> createdFiles;
        destinationPath = destinationPath.concat(AutomationFrameworkConstants.PATH_DELIMITER + keywordsFolderOnFramework);
        createdFiles = createKeywordsFiles(inputFilePath, destinationPath);
        generateKeywordsFileContent(inputFilePath, createdFiles);
    }
}