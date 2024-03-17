package course2.chapter11.ex11_1;

import course2.chapter11.DirectoryStructure;
import course2.chapter11.View;

public class Main {
    public static void main(String[] args) {
        String filePath= View.Input();

        DirectoryStructure directoryStructure = new DirectoryStructure();
        directoryStructure.makeDirectoryStructure(filePath);
        System.out.println(directoryStructure.getDirectoryStructure());
    }
}
