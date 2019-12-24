package AutomationFolder;

import java.io.File;
import java.io.IOException;

public class AutomationFolder {
    public boolean folderExists(String path) {
        return new File(path).exists();
    }

    public void createFolder(String path){
        File file = new File(path);
        file.mkdir();
    }

    public void createFile(String path){
        File file=new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
