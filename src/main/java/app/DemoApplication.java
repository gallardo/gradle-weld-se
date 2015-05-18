package app;

import dao.HibernateUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DemoApplication {
    @Inject private EntityManager em;

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
