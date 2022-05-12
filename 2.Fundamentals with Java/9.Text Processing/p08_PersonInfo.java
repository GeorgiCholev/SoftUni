package TextProcessing_9.More_Exercise;

import java.util.Scanner;

public class p01_PersonInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            findInfo(info, line, '@', '|', " is ");
            findInfo(info, line, '#', '*', " years old.");
            System.out.println(info);
            info.setLength(0);
        }
    }

    private static void findInfo(StringBuilder info, String line, char start, char end, String additive) {
        int index = line.indexOf(start);
        for (int j = index + 1; j < line.length(); j++) {
            if (line.charAt(j) != end) {
                info.append(line.charAt(j));
            } else {
                info.append(additive);
                break;
            }
        }
    }
}

