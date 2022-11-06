import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Map;

public class p12_EmployeesMaximumSalaries {

    private static final BigDecimal FLOOR = BigDecimal.valueOf(30_000);
    private static final BigDecimal CEILING = BigDecimal.valueOf(70_000);

    public static void main(String[] args) {

        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        manager.createQuery("SELECT e.department.name, MAX(e.salary)" +
                                "FROM Employee e " +
                                "GROUP BY e.department " +
                                "HAVING MAX(e.salary) NOT BETWEEN :floor AND :ceiling",
                        Object[].class)
                .setParameter("floor", FLOOR)
                .setParameter("ceiling", CEILING)
                .getResultList()
                .forEach(o -> System.out.println(o[0] + " " + o[1]));


        manager.getTransaction().commit();
        manager.close();
    }
}
