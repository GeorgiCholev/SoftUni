import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class p01_SetUp {

    private static final String PERSISTENCE_UNIT_NAME = "soft_uni";

    public static EntityManager getEntityManager() {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        return factory.createEntityManager();
    }

}

