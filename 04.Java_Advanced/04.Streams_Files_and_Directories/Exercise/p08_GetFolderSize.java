package StreamsFilesDirectories_04.Exercise;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class p08_GetFolderSize {
    public static void main(String[] args) {
        Deque<File> folders = new ArrayDeque<>();
        File folder = new File("src/StreamsFilesDirectories_04/Exercise/resources");
        folders.offer(folder);
        int sum = 0;
        while (folders.size() != 0) {
            File[] nestedFiles = folders.poll().listFiles();
            for (File nestedFile : nestedFiles) {
                if (nestedFile.isDirectory()) {
                    folders.offer(nestedFile);
                } else {
                    sum += nestedFile.length();
                }
            }
        }
        System.out.println(sum);
    }
}
