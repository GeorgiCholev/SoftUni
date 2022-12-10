package StreamsFilesDirectories_04.Exercise;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class p04_CountCharacterTypes {
    public static void main(String[] args) {
        String inputPath = "src/StreamsFilesDirectories_04/Exercise/resources/input.txt";
        String outputPath = "src/StreamsFilesDirectories_04/Exercise/resources/p04-output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             PrintWriter writer = new PrintWriter(outputPath)) {

            Set<Character> punctuations = new HashSet<>(Arrays.asList('!', ',', '.', '?'));
            Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

            int vowelsCount = 0;
            int consonantsCount = 0;
            int punctuationsCount = 0;

            String line = reader.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    char symbol = line.charAt(i);
                    if (punctuations.contains(symbol)) {
                        punctuationsCount++;
                    } else if (vowels.contains(symbol)) {
                        vowelsCount++;
                    } else if (!Character.isWhitespace(symbol)) {
                        consonantsCount++;
                    }
                }

                line = reader.readLine();
            }
            writer.print("Vowels: " + vowelsCount + System.lineSeparator() + "Consonants: " + consonantsCount
            + System.lineSeparator() + "Punctuation: " + punctuationsCount);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
