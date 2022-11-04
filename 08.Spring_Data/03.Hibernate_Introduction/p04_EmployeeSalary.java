import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class p04_EmployeeSalary {

    public static final BigDecimal SALARY_NEEDED = BigDecimal.valueOf(50_000);

    public static void main(String[] args) {
        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        manager
                .createQuery("SELECT e.firstName FROM Employee e WHERE e.salary > :needed",
                        String.class)
                .setParameter("needed", SALARY_NEEDED)
                .getResultStream()
                .forEach(System.out::println);

        manager.getTransaction().commit();
        manager.close();
    }
}
