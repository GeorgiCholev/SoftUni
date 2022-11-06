import entities.Project;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.function.Function;

public class p09_FindLatest10Projects {
    public static void main(String[] args) {

        EntityManager manager = p01_SetUp.getEntityManager();

        manager.getTransaction().begin();

        manager.createQuery("FROM Project p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .sorted(Comparator.comparing(Project::getName))
                .map(p -> outputFormat(p))
                .forEach(System.out::println);

        manager.getTransaction().commit();
        manager.close();
    }

    private static String outputFormat(Project p) {

        String name = "Project name: " + p.getName();
        String description = " Project Description: " + p.getDescription();
        String startDate = "Project Start Date: " +
                (p.getStartDate() != null ?
                        p.getStartDate().minusHours(3)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")) : "null");
        String endDate = "Project End Date: " +
                (p.getEndDate() != null ?
                        p.getEndDate().minusHours(3)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")) : "null");

        return name + "\n\t" + description + "\n\t" + startDate + "\n\t" + endDate;
    }
}
