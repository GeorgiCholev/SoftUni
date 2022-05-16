package RegEx_10.Exercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p06_Emails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String line = scanner.nextLine();
        Pattern email = Pattern.compile("(\\s+)([A-Za-z\\d]+[._-]*[A-Za-z\\d]+@[A-Za-z]+-*[A-Za-z]+(\\.[A-Za-z]+)+)");
        Matcher matcher = email.matcher(line);
        while (matcher.find()){
            System.out.println(matcher.group(2));
        }
    }
}
