import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class p10_IncreaseSalaries {

    private static final List<String> DEPARTMENTS = new ArrayList<>(
            Arrays.asList(
                    "Engineering",
                    "Tool Design",
                    "Marketing",
                    "Information Services"
            )
    );

    public static void main(String[] args) {

        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        List<Employee> employeesForRaise = manager
                .createQuery("FROM Employee e WHERE e.department.name IN (:departments)", Employee.class)
                .setParameter("departments", DEPARTMENTS)
                .getResultList();

        employeesForRaise.forEach(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));

        employeesForRaise.forEach(printNeededFormat());

        manager.getTransaction().commit();
        manager.close();
    }

    private static Consumer<Employee> printNeededFormat() {
        return e -> System.out.println(
                e.getFirstName() + " " + e.getLastName() + " ($" + String.format("%.2f", e.getSalary()) + ")"
        );
    }
}
