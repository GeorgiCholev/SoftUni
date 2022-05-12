package Maps_8.Exercise;

import java.util.*;

public class p08_Company {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, List<String>> employeesOfCompany = new LinkedHashMap<>();
        while (!input.equals("End")) {
            String[] components = input.split(" -> ");
            String company = components[0];
            String employeeID = components[1];
            employeesOfCompany.putIfAbsent(company, new ArrayList<>());
            if (!employeesOfCompany.get(company).contains(employeeID)) {
                employeesOfCompany.get(company).add(employeeID);
            }
            input = scanner.nextLine();
        }

        employeesOfCompany.forEach((key, value) -> {
            System.out.println(key);
            value.forEach(e -> System.out.println("-- " + e));
        });
    }
}
