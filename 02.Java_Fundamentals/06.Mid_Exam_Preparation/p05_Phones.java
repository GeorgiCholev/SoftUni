package MidExam_6.Exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class p03_Phones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> phones = new ArrayList<>(Arrays.asList(input.split(", ")));
        String command = scanner.nextLine();
        while (!command.equals("End")) {
            String[] components = command.split(" ");
            String type = components[0];
            String model;
        switch (type) {
            case "Add":
                model = components[2];
                if (!phones.contains(model)) {
                phones.add(model);
                } else {
                    command = scanner.nextLine();
                    continue;
                }
                break;
            case "Remove":
                model = components[2];
                if (phones.contains(model)) {
                    phones.remove(model);
                } else {
                    command = scanner.nextLine();
                    continue;
                }
                break;
            case "Bonus":
                String[] oldAndNew = components[3].split(":");
                String oldPhone = oldAndNew[0];
                String newPhone = oldAndNew[1];
                if (phones.contains(oldPhone)) {
                int oldPhoneIndex = phones.indexOf(oldPhone);
                    phones.add(oldPhoneIndex + 1, newPhone);
                } else {
                    command = scanner.nextLine();
                    continue;
                }
                break;
            case "Last":
                model = components[2];
                if (phones.contains(model)) {
                    phones.remove(model);
                    phones.add(model);
                } else {
                    command = scanner.nextLine();
                    continue;
                }
                break;
        }
            command = scanner.nextLine();
        }
        printListString(phones);
}


    private static void printListString(List<String> texts) {
        for (int i = 0; i < texts.size(); i++) {
            System.out.print(texts.get(i));
            if (i != texts.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
