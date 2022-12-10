package StreamsFilesDirectories_04.Exercise;

import java.io.*;
import java.util.Scanner;

public class p05_LineNumbers {
    public static void main(String[] args) {
        String inputPath = "src/StreamsFilesDirectories_04/Exercise/resources/inputLineNumbers.txt";
        String outputPath = "src/StreamsFilesDirectories_04/Exercise/resources/p05-output.txt";

        try (Scanner scanner = new Scanner(new FileReader(inputPath));
             PrintWriter writer = new PrintWriter(new FileWriter(outputPath))) {

            int counter = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                writer.println(counter + ". " + line);
                counter++;
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
