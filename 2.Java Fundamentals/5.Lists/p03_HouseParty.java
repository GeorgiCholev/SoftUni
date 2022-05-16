package Lists_5.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p03_HouseParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        List<String> namesAttending = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String decision = scanner.nextLine();
            String[] decisionArray = decision.split(" ");
            String name = decisionArray[0];
            if (decisionArray.length == 3) {
                boolean alreadyAttending = false;
                for (String nameAttending : namesAttending) {
                    if (nameAttending.equals(name)) {
                        alreadyAttending = true;
                        break;
                    }
                }
                if (alreadyAttending) {
                    System.out.printf("%s is already in the list!%n", name);
                } else {
                    namesAttending.add(name);
                }
            } else {
                boolean alreadyInList = false;
                for (String nameAttending : namesAttending) {
                    if (nameAttending.equals(name)) {
                        alreadyInList = true;
                        break;
                    }
                }
                if (alreadyInList) {
                    namesAttending.remove(name);
                } else {
                    System.out.printf("%s is not in the list!%n", name);
                }
            }
        }
        namesAttending.forEach(e -> System.out.println(e + " "));
    }
}
