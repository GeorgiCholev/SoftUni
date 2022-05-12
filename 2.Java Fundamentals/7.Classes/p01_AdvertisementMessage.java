package Classes_7.Exercises;

import java.util.Random;
import java.util.Scanner;

public class p01_AdvertisementMessage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberMessages = Integer.parseInt(scanner.nextLine());
        AdvertisementMessages first = new AdvertisementMessages();
        Random random;
        for (int i = 0; i < numberMessages; i++) {
            random = new Random();
            int num1 = random.nextInt(first.phrases.length);
            int num2 = random.nextInt(first.events.length);
            int num3 = random.nextInt(first.authors.length);
            int num4 = random.nextInt(first.cities.length);
            first.RandomPropertiesPrinted(num1, num2, num3, num4);
        }
    }

    static class AdvertisementMessages {
        String[] phrases = {"Excellent product.", "Such a great product.", "I always use that product.", "Best product of its category.", "Exceptional product.", "I can’t live without this product."};
        String[] events = {"Now I feel good.", "I have succeeded with this product.", "Makes miracles. I am happy of the results!", "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!"};
        String[] authors = {"Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};
        String[] cities = {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};

        void RandomPropertiesPrinted(int num1, int num2, int num3, int num4) {
            String[] properties = new String[4];
            properties[0] = this.phrases[num1];
            properties[1] = this.events[num2];
            properties[2] = this.authors[num3];
            properties[3] = this.cities[num4];
            System.out.printf("%s %s %s – %s%n", properties[0], properties[1], properties[2], properties[3]);
        }
    }
}
