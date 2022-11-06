import entities.Address;

import javax.persistence.EntityManager;
import java.util.function.Function;

public class p07_AddressesWithEmployeeCount {
    public static void main(String[] args) {

        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        manager.createQuery("FROM Address a ORDER BY a.employees.size DESC",
                        Address.class)
                .setMaxResults(10)
                .getResultStream()
                .map(outputFormat())
                .forEach(System.out::println);

        manager.getTransaction().commit();
        manager.close();
    }

    private static Function<Address, String> outputFormat() {
        return a -> a.getText() + " - " + a.getEmployees().size() + " employees";
    }
}
