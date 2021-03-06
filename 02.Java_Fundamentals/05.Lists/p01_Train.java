package Lists_5.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p01_Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> train = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int max = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        while (!line.equals("end")) {
            String[] data = line.split(" ");
            if (data[0].equals("Add")) {
                train.add(Integer.parseInt(data[1]));
            } else {
                int newPassengers = Integer.parseInt(data[0]);
                for (int i = 0; i < train.size(); i++) {
                    if (newPassengers + train.get(i) <= max) {
                        train.set(i, train.get(i) + newPassengers);
                        break;
                    }
                }
            }
            line = scanner.nextLine();
        }
       train.forEach(el -> System.out.print(el + " "));
    }
}
