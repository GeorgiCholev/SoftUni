import entities.Address;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class p06_AddAddressToEmployee {

    private static final String NEW_ADDRESS_TEXT = "Vitoshka 15";

    public static void main(String[] args) {

        String lastName = new Scanner(System.in).nextLine();

        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        Address address = new Address();
        address.setText(NEW_ADDRESS_TEXT);

        manager.persist(address);

        manager.createQuery("UPDATE Employee e SET e.address = :adr WHERE e.lastName = :last_name")
                .setParameter("adr", address)
                .setParameter("last_name", lastName)
                .executeUpdate();

        manager.getTransaction().commit();
        manager.close();
    }
}
