import entities.Employee;

import javax.persistence.EntityManager;
import java.util.function.Function;

public class p05_EmployeesFromDepartment {

    private static final String NEEDED_DEPARTMENT_NAME = "Research and Development";

    public static void main(String[] args) {
        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        manager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.name = :dpt_name " +
                        "ORDER BY e.salary, e.id", Employee.class)
                .setParameter("dpt_name", NEEDED_DEPARTMENT_NAME)
                .getResultList()
                .stream()
                .map(outputFormat())
                .forEach(System.out::println);

        manager.getTransaction().commit();
        manager.close();
    }
    private static Function<Employee, String> outputFormat() {
        return e -> e.getFirstName() + " " + e.getLastName() + " from " +
                e.getDepartment().getName() + " - " + String.format("%.2f", e.getSalary());
    }
}
