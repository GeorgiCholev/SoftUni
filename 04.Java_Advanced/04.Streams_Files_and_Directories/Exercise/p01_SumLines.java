package StreamsFilesDirectories_04.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class p01_SumLines {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader
                ("src/StreamsFilesDirectories_04/Exercise/resources/input.txt"))) {

            String line = reader.readLine();
            while (line != null) {
                int sum = 0;
                for (int i = 0; i < line.length(); i++) {
                    sum += line.charAt(i);
                }
                System.out.println(sum);
                line = reader.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
