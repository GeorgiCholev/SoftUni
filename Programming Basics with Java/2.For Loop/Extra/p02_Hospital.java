package For_Loop_4.Extra;

import java.util.Scanner;

public class p02_Hospital {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int period = Integer.parseInt(scanner.nextLine());

        int treatedPatients = 0;
        int untreatedPatients = 0;
        int numberDoctors = 7;

        for (int i = 1; i<= period; i++) {
            int numberPatients = Integer.parseInt(scanner.nextLine());
            if (i % 3 == 0) {
                if (untreatedPatients > treatedPatients)
                    numberDoctors++;
            }
            if (numberPatients <= numberDoctors) {
                treatedPatients += numberPatients;
            } else {
                treatedPatients += numberDoctors;
                untreatedPatients += numberPatients - numberDoctors;
            }

        }
        System.out.printf("Treated patients: %d.%n", treatedPatients);
        System.out.printf("Untreated patients: %d.", untreatedPatients);
    }
}
