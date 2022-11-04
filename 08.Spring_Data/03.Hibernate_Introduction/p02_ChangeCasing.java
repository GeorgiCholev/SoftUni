import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class p02_ChangeCasing {
    public static void main(String[] args) {
        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        TypedQuery<Town> query = manager.createQuery("SELECT t FROM Town t", Town.class);
        List<Town> towns = query.getResultList();

        for (Town town : towns) {
            String townName = town.getName();
            if (townName.length() <= 5) {
                town.setName(townName.toUpperCase());
            }
        }

        manager.getTransaction().commit();
        manager.close();
    }
}
