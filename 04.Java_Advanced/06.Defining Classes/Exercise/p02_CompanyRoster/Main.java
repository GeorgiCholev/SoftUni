package DefiningClasses_06.Exercise.p02_CompanyRoster;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Map<String, List<Employee>> departmentStaff = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] employeeInfo = scanner.nextLine().split("\\s+");
            Employee employee = constructEmployeeWithInfo(employeeInfo);
            String department = employee.getDepartment();
            departmentStaff.putIfAbsent(department, new ArrayList<>());
            departmentStaff.get(department).add(employee);
        }

        String department = highestPaidDepartment(departmentStaff);
        Comparator<Employee> highestSalary = (first, second) -> Double.compare(second.getSalary(), first.getSalary());

        System.out.println("Highest Average Salary: " + department);
        departmentStaff.get(department).stream().sorted(highestSalary)
                .forEach(System.out::println);

    }

    private static String highestPaidDepartment(Map<String, List<Employee>> departmentStaff) {
        double highestAverage = -1;
        String highestPaidDepartment = "";
        for (var entry : departmentStaff.entrySet()) {
            int salarySum = 0;
            for (Employee e : entry.getValue()) {
                salarySum += e.getSalary();
            }
            double entryAverage = (salarySum * 1.0) / entry.getValue().size();
            if (entryAverage > highestAverage) {
                highestAverage = entryAverage;
                highestPaidDepartment = entry.getKey();
            }
        }
        return highestPaidDepartment;
    }

    private static Employee constructEmployeeWithInfo(String[] employeeInfo) {

        String name = employeeInfo[0];
        double salary = Double.parseDouble(employeeInfo[1]);
        String position = employeeInfo[2];
        String department = employeeInfo[3];
        String email;
        int age;

        switch (employeeInfo.length) {
            case 4:
                return new Employee(name, salary, position, department);

            case 5:
                if (employeeInfo[4].matches("\\d+")) {
                    age = Integer.parseInt(employeeInfo[4]);
                    return new Employee(name, salary, position, department, age);
                } else {
                    email = employeeInfo[4];
                    return new Employee(name, salary, position, department, email);
                }

            case 6:
                email = employeeInfo[4];
                age = Integer.parseInt(employeeInfo[5]);
                return new Employee(name, salary, position, department, email, age);

            default:
                throw new IllegalArgumentException("Illegal Input");
        }
    }
}
