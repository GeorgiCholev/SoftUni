package StreamsFilesDirectories_04.Exercise;

import java.io.*;
import java.util.*;

public class p06_WordCount {
    public static void main(String[] args) {
        String wordsToCountPath = "src/StreamsFilesDirectories_04/Exercise/resources/words.txt";
        String containerToCheck = "src/StreamsFilesDirectories_04/Exercise/resources/text.txt";
        String outputResults = "src/StreamsFilesDirectories_04/Exercise/resources/p06-output.txt";

        try (Scanner readWords = new Scanner(new FileReader(wordsToCountPath));
             Scanner scanWordContainer = new Scanner(new FileReader(containerToCheck));
             PrintWriter writer = new PrintWriter(new FileWriter(outputResults))) {

            Map<String, Integer> occurrencesOfWords = new LinkedHashMap<>();
            Arrays.stream(readWords.nextLine().split("\\s+")).forEach(w -> occurrencesOfWords.put(w, 0));

            while (scanWordContainer.hasNext()) {
                String word = scanWordContainer.next();
                if (occurrencesOfWords.containsKey(word)) {
                    occurrencesOfWords.put(word, occurrencesOfWords.get(word) + 1);
                }
            }
            occurrencesOfWords.forEach((key, value) -> writer.print(key + " - " + value + System.lineSeparator()));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
