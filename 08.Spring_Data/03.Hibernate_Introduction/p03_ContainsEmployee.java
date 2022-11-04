import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class p03_ContainsEmployee {
    public static void main(String[] args) {
        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        String[] employeeName = new Scanner(System.in).nextLine().split("\\s+");
        String firstName = employeeName[0];
        String lastName = employeeName[1];

        long numberOfMatches = manager
                .createQuery("SELECT count(e) FROM Employee e " +
                        "WHERE e.firstName = :fn AND e.lastName = :ln", Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();

        String foundEmployee = numberOfMatches == 0 ? "No" : "Yes";
        System.out.print(foundEmployee);

        manager.getTransaction().commit();
        manager.close();
    }
}
