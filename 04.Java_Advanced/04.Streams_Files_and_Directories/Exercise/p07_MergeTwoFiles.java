package StreamsFilesDirectories_04.Exercise;

import java.io.*;
import java.util.Scanner;

public class p07_MergeTwoFiles {
    public static void main(String[] args) {
        String inputOne = "src/StreamsFilesDirectories_04/Exercise/resources/inputOne.txt";
        String inputTwo = "src/StreamsFilesDirectories_04/Exercise/resources/inputTwo.txt";
        String output = "src/StreamsFilesDirectories_04/Exercise/resources/p07-output.txt";

        try (Scanner scannerOne = new Scanner(new FileReader(inputOne));
             Scanner scannerTwo = new Scanner(new FileReader(inputTwo));
             PrintWriter writer = new PrintWriter(new FileWriter(output, true))) {

            fillOutput(scannerOne, writer);
            fillOutput(scannerTwo, writer);

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillOutput(Scanner scanner, PrintWriter writer) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            writer.println(line);

        }
    }
}
