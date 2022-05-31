package StreamsFilesDirectories_04.Exercise;

import java.io.*;

public class p03_AllCapitals {
    public static void main(String[] args) {
        String inputPath = "src/StreamsFilesDirectories_04/Exercise/resources/input.txt";
        String outputPath = "src/StreamsFilesDirectories_04/Exercise/resources/p03-output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             PrintWriter writer = new PrintWriter(new FileWriter(outputPath))) {
            reader.lines().forEach(l -> writer.println(l.toUpperCase()));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
