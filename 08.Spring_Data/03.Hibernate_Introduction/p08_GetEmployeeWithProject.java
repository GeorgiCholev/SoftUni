import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p08_GetEmployeeWithProject {
    public static void main(String[] args) {
        int employeeId = new Scanner(System.in).nextInt();

        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        Employee employeeFromId = manager.find(Employee.class, employeeId);

        System.out.print(getOutputFormatFor(employeeFromId));

        manager.getTransaction().commit();
        manager.close();
    }

    private static String getOutputFormatFor(Employee e) {
        String firstName = e.getFirstName();
        String lastName = e.getLastName();
        String jobTitle = e.getJobTitle();
        String projects = e.getProjects()
                .stream()
                .map(p -> "\t" + p.getName())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(System.lineSeparator()));

        return firstName + " " + lastName + " - " + jobTitle + "\n" + projects;
    }
}
