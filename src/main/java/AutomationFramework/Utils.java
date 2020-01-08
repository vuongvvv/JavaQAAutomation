package AutomationFramework;

import org.apache.commons.text.WordUtils;

public class Utils {
    public static String formatKeywordString(String keyword){
        String returnKeyword = keyword.toLowerCase().replace("_"," ").trim();
        returnKeyword = WordUtils.capitalizeFully(returnKeyword);
        return returnKeyword;
    }
}
