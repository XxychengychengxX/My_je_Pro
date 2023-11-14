package EXP_IN_SCHOOL.compilerea;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CheckCharHandler {




    public static void main(String[] args) throws IOException {
        File file = new File("myc.txt");
        if (!file.exists())
            return;
        FileInputStream CharReader = new FileInputStream(file);
        byte[] chars = new byte[(int) file.length()];
        int temp;
        CharReader.read(chars);
        String s = new String(chars);
        String[] strings = s.split("\r\n");
        for (int i = 0; i < strings.length; i++) {
            CheckChar.charahander(strings[i]);
        }
    }


}
