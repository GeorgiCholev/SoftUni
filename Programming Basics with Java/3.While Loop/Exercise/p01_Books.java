package While_Loop_5.Exercise;

import java.util.Scanner;

public class p01_Books {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String searchedBook = scanner.nextLine();
        String nextBook = scanner.nextLine();

        boolean foundBook = false;
        int checkedBooks = 0;

        while (!nextBook.equals("No More Books")) {
            if (nextBook.equals(searchedBook)) {
                foundBook = true;
                break;
            }
            checkedBooks++;
            nextBook = scanner.nextLine();
        }
        if (foundBook) {
            System.out.printf("You checked %d books and found it.", checkedBooks);
        } else {
            System.out.printf("The book you search is not here!%n" +
                    "You checked %d books.", checkedBooks);
        }
    }
}
