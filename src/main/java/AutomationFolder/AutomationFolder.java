package AutomationFolder;

import java.io.File;

public class AutomationFolder {
    public boolean folderExists(String path) {
        return new File(path).exists();
    }

    public void createFolder(String path){
        File file = new File(path);
        file.mkdir();
    }
}
