package app;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

@Singleton
public class DemoApplication {
    @Inject
    private EntityManager em;

    public void run() {
        try {
            em.getTransaction().begin();
            System.out.println("Inside transaction");
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            em.getTransaction().rollback();
        }
    }
}
