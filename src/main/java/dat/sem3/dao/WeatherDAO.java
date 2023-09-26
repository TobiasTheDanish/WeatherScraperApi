package dat.sem3.dao;

import dat.sem3.config.HibernateConfig;
import dat.sem3.model.WeatherEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

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
            if (weatherEntity.getId() == null) {
                em.getTransaction().begin();
                em.persist(weatherEntity);
                em.getTransaction().commit();
            }
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

    public WeatherEntity updateWeatherEntity (WeatherEntity weatherEntity) {
        if (weatherEntity.getId() != null){
            try(EntityManager em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.merge(weatherEntity);
                em.getTransaction().commit();
            }
        }
        return weatherEntity;
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

    public List<WeatherEntity> getWeatherFromTodayAnd6DaysAhead(){
            try (EntityManager em = emf.createEntityManager()){
                LocalDate today = LocalDate.now();
                LocalDate yesterday = today.minusDays(1);
                LocalDate sixDaysAhead = today.plusDays(6);

                em.getTransaction().begin();
                TypedQuery<WeatherEntity> query = em.createQuery(
                        "SELECT w FROM WeatherEntity w WHERE w.date BETWEEN :startDate AND :endDate", WeatherEntity.class);
                query.setParameter("startDate",yesterday);
                query.setParameter("endDate", sixDaysAhead);

                List<WeatherEntity> weatherEntities = query.getResultList();
                em.getTransaction().commit();

                return weatherEntities;
            }
    }
}
