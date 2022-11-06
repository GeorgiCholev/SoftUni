import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class p11_FindEmployeesByFirstName {
    public static void main(String[] args) {

        String namePattern = new Scanner(System.in).nextLine();

        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        manager
                .createQuery("FROM Employee e WHERE e.firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", namePattern + "%")
                .getResultList()
                .forEach(printOutputFormat());


        manager.getTransaction().commit();
        manager.close();
    }

    private static Consumer<Employee> printOutputFormat() {

        return e -> System.out.println(
                e.getFirstName() + " " + e.getLastName() + " - " + e.getJobTitle() + " - " + e.getSalary()
        );
    }
}
