package course2.chapter11.ex11_2;

import course2.chapter11.DirectoryStructure;
import course2.chapter11.View;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = View.Input();
        FileHandler fileHandler = new FileHandler();
        DirectoryStructure directoryStructure = new DirectoryStructure();
        directoryStructure.makeDirectoryStructure(filePath);
        List<String> fileList=directoryStructure.getFilePathList();

        for(String file : fileList){
            try {
                fileHandler.readFile(file);
                System.out.println(" 라인 수 : "+fileHandler.countLine());
            } catch (FileNotFoundException e) {
                System.out.println("해당 파일을 찾을 수 없습니다.");
            } catch (IOException e) {
                System.out.println("입출력에 예외가 발생하였습니다.");
            }
        }
    }
}
