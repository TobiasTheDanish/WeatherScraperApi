package dat.sem3;

import dat.sem3.config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("weatherForecast");
        EntityManager em = emf.createEntityManager();
        em.getTransaction();
        em.close();
    }
}
