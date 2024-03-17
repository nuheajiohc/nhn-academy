package course2.chapter11;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryStructure {

    private List<String> filePathList;
    private StringBuilder sb;

    public DirectoryStructure(){
        filePathList = new ArrayList<>();
        sb = new StringBuilder();
    }

    public void makeDirectoryStructure(String directoryName) {
        File directory = new File(directoryName.trim());
        makeDirectory(directory);
    }

    private void makeDirectory(File directory) {
        sb.append(directory).append(" 폴더\n");
        makeFile(directory);
    }

    private void makeFile(File directory) {
        String[] files = directory.list();
        for (String file : files) {
            File fileInDirectory = new File(directory.getPath() + "/" + file);
            if(fileInDirectory.isDirectory()){
                makeDirectory(fileInDirectory);
                continue;
            }
            filePathList.add(fileInDirectory.getPath());
            sb.append(file).append("\n");
        }
    }

    public String getDirectoryStructure(){
        return sb.toString();
    }

    public List<String> getFilePathList(){
        return filePathList;
    }
}
