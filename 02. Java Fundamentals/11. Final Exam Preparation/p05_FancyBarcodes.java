package Fundamentals_ExamPrep_11.April4th2020;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02_FancyBarcodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Pattern barcodePattern = Pattern.compile("^@#+[A-Z]([A-Za-z\\d]){4,}[A-Z]@#+$");
        Pattern group = Pattern.compile("\\d+");
        for (int i = 0; i < n; i++) {
            String barcode = scanner.nextLine();
            String validGroup = processBarcode(barcodePattern, barcode);
            if (validGroup != null) {
                System.out.println("Product group: " + validGroup);
            } else {
                System.out.println("Invalid barcode");
            }
        }
    }

    private static String processBarcode(Pattern barcodePattern, String barcode) {
        Matcher matcher = barcodePattern.matcher(barcode);
        StringBuilder currentGroup = new StringBuilder();
        if (matcher.matches()) {
            Pattern group = Pattern.compile("\\d+");
            matcher = group.matcher(barcode);
            if (matcher.find()) {
                currentGroup.append(matcher.group());
                while (matcher.find()) {
                    currentGroup.append(matcher.group());
                }
            } else {
                currentGroup.append("00");
            }
        } else {
            return null;
        }
        return currentGroup.toString();
    }
}
