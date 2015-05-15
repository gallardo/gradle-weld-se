package dao;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Utility class for configuring an {@link EntityManagerFactory} and creating
 * {@link EntityManager}s.
 * <p>
 * In the absence of full JPA capabilities in a Java SE context, we have to
 * resort to programmatic provision of EntityManagers. If the application is
 * deployed in a real application server, EntityManagers can be obtained from
 * the container using the {@link javax.persistence.PersistenceContext}
 * annotation.
 */
public class HibernateUtil {
    private static final EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            final String persistenceUnit = System.getProperty(
                    "persistenceUnit", "persistenceUnit");
            return Persistence.createEntityManagerFactory(persistenceUnit);
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial EntityManagerFactory creation failed."
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Produces
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    @Produces
    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public static void closeEntityManager(@Disposes EntityManager em) {
        System.out.println("Closing EM");
        try {
            em.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void closeEntityManagerFactory(
            @Disposes EntityManagerFactory emf) {
        System.out.println("Closing EMF");
        try {
            emf.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}