package AutomationFramework;

import AutomationFolder.AutomationFolder;
import CSV.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class Keywords {
    private static final String keywordsPostfix = "_keywords.robot";
    private static final String keywordsFolderOnFramework = "keywords";

    public List<String> createKeywordsFiles(String inputFilePath, String parentpath) {
        AutomationFolder automationFolder = new AutomationFolder();
        CSVReader csvReader = new CSVReader();
        List<String> featuresList = new ArrayList<>();
        List<String> subFeaturesList = new ArrayList<>();
        List<String> apiNameList = new ArrayList<>();
        List<String> createdFiles = new ArrayList<String>();

        featuresList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.FEATURE.toString());
        subFeaturesList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.SUB_FEATURE.toString());
        apiNameList = csvReader.readCSVFileByHeader(inputFilePath, TestCaseEnum.API_NAME.toString());
        for (int i = 0; i < featuresList.size(); i++) {
            String fullPathFeature = parentpath.concat(AutomationFrameworkConstants.PATH_DELIMITER + featuresList.get(i).toLowerCase());
            String fullPathSubFeatureKeywordsFile = fullPathFeature.concat(AutomationFrameworkConstants.PATH_DELIMITER + subFeaturesList.get(i).toLowerCase() + keywordsPostfix);
            automationFolder.createFolder(fullPathFeature);
            automationFolder.createFile(fullPathSubFeatureKeywordsFile);
            createdFiles.add(fullPathSubFeatureKeywordsFile);
        }
        return createdFiles;
    }

    public void generatingKeywordsFileFromInputFile(String inputFilePath, String destinationPath) {
        List<String> createdFiles = new ArrayList<>();
        destinationPath = destinationPath.concat(AutomationFrameworkConstants.PATH_DELIMITER + keywordsFolderOnFramework);
        createdFiles = createKeywordsFiles(inputFilePath, destinationPath);
    }
}