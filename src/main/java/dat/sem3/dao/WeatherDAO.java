package dat.sem3.dao;

import dat.sem3.config.HibernateConfig;
import dat.sem3.model.WeatherEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class WeatherDAO {

    private final EntityManagerFactory emf;
    private static WeatherDAO instance;

    private WeatherDAO() {
        emf = HibernateConfig.getEntityManagerFactoryConfig("weatherForecast");
    }

    public static WeatherDAO getInstance() {
        if (instance == null) {
            instance = new WeatherDAO();
        }
        return instance;
    }

    public WeatherEntity createWeatherEntity(WeatherEntity weatherEntity){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(weatherEntity);
            em.getTransaction().commit();
        }
        return weatherEntity;
    }

    public WeatherEntity readWeatherEntityById(int id){
        WeatherEntity weatherEntity;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            weatherEntity = em.find(WeatherEntity.class,id);
        }
        return weatherEntity;
    }

    public void updateWeatherEntity (WeatherEntity weatherEntity) {
        if (weatherEntity.getId() != null){
            try(EntityManager em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.merge(weatherEntity);
                em.getTransaction().commit();
            }
        }
    }

    public void deleteWeatherEntity(int id) {
        WeatherEntity weatherEntity = readWeatherEntityById(id);
        if (weatherEntity.getId() != null) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.remove(weatherEntity);
                em.getTransaction().commit();
            }
        }
    }
}
