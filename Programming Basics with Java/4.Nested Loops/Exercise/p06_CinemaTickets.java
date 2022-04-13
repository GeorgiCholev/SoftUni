package Nested_Loops_6.Exercise;

import java.util.Scanner;

public class p06_CinemaTickets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String movieName = scanner.nextLine();

        int totalSeats = 0;
        int kidSeats = 0;
        int studentSeats = 0;
        int standardSeats = 0;


        while (!movieName.equals("Finish")) {
            int seats = Integer.parseInt(scanner.nextLine());
            String typeSeat = scanner.nextLine();
            int totalSeatsThisMovie = 0;
            int seatsLeft = seats;
            while (!typeSeat.equals("End")) {
                totalSeats++;
                totalSeatsThisMovie++;
                switch (typeSeat) {
                    case "kid":
                        kidSeats++;
                        seatsLeft--;
                        break;
                    case "student":
                        studentSeats++;
                        seatsLeft--;
                        break;
                    case "standard":
                        standardSeats++;
                        seatsLeft--;
                        break;
                }
                if (seatsLeft == 0) {
                    break;
                }
                typeSeat = scanner.nextLine();
            }
            double percentageFull = (totalSeatsThisMovie * 1.0 / seats) * 100;
            System.out.printf("%s - %.2f%% full.%n", movieName, percentageFull);
            movieName = scanner.nextLine();
        }
        System.out.printf("Total tickets: %d%n" +
                "%.2f%% student tickets.%n" +
                "%.2f%% standard tickets.%n" + "%.2f%% kids tickets.", totalSeats, (studentSeats * 1.0 / totalSeats) * 100, (standardSeats * 1.0 / totalSeats) * 100, (kidSeats * 1.0 / totalSeats) * 100);
    }
}
