package Nested_Loops_6.Extra;

import java.util.Scanner;

public class p05_Wedding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int men = Integer.parseInt(scanner.nextLine());
        int women = Integer.parseInt(scanner.nextLine());
        int tables = Integer.parseInt(scanner.nextLine());
        int totalTables = 0;

            for (int j = 1; j <= men; j++) {
                for (int k = 1; k<= women; k++) {
                    totalTables++;
                    if (totalTables <= tables) {
                        System.out.printf("(%d <-> %d)" + " ", j, k);
                    } else {
                        break;
                    }
                }
                if (tables < totalTables) {
                    break;
                }
            }
        }
    }

