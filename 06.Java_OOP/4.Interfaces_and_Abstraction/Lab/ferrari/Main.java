package _4_Abstraction_and_Interfaces.Lab.ferrari;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String driver = new Scanner(System.in).nextLine();

        System.out.println(new Ferrari(driver));

    }

}
