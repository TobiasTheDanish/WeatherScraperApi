package dat.sem3.dao;

import dat.sem3.config.HibernateConfig;
import dat.sem3.model.WeatherDescription;
import dat.sem3.model.WeatherDescriptionEntity;
import dat.sem3.model.WeatherEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDAOTest {


    WeatherEntity weatherEntity;
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("weatherForecast");
    WeatherDAO dao;


    @BeforeEach
    void setup(){
        dao = WeatherDAO.getInstance();
        WeatherDescriptionEntity weatherDesc = new WeatherDescriptionEntity();
        weatherDesc.setAfternoon(WeatherDescription.CLOUDY);
        weatherDesc.setEvening(WeatherDescription.RAIN_THUNDER);
        weatherDesc.setNight(WeatherDescription.SLEET);
        weatherDesc.setMorning(WeatherDescription.SNOW);
        weatherEntity = WeatherEntity.builder()
                .date(LocalDate.now())
                .temperature(28)
                .wind(7)
                .rain(0)
                .minTemperature(15)
                .maxTemperature(31)
                .weatherDescription(weatherDesc)
                .build();

        dao.createWeatherEntity(weatherEntity);
    }

    @AfterEach
    void tearDown(){
        dao.deleteWeatherEntity(weatherEntity.getId());
    }



    @Test
    void getInstance() {
        assertNotNull(dao);
        WeatherDAO dao1 = WeatherDAO.getInstance();
        WeatherDAO dao2 = WeatherDAO.getInstance();
        assertSame(dao1, dao2);
    }

    @Test
    void createWeatherEntity() {
        weatherEntity = dao.createWeatherEntity(weatherEntity);
        assertNotNull(weatherEntity.getId());
    }

    @Test
    void readWeatherEntityById() {
        WeatherEntity weatherEntity1 = dao.readWeatherEntityById(weatherEntity.getId());
        assertEquals(weatherEntity.getId(), weatherEntity1.getId());
    }

    @Test
    void updateWeatherEntity() {
        weatherEntity.setTemperature(69);
        weatherEntity = dao.updateWeatherEntity(weatherEntity);
        assertEquals(69, weatherEntity.getTemperature());
    }

    @Test
    void deleteWeatherEntity() {
        WeatherDescriptionEntity weatherDesc = new WeatherDescriptionEntity();
        weatherDesc.setAfternoon(WeatherDescription.CLOUDY);
        weatherDesc.setEvening(WeatherDescription.RAIN_THUNDER);
        weatherDesc.setNight(WeatherDescription.SLEET);
        weatherDesc.setMorning(WeatherDescription.SNOW);
        WeatherEntity weather = WeatherEntity.builder()
                .date(LocalDate.now())
                .temperature(28)
                .wind(7)
                .rain(0)
                .minTemperature(15)
                .maxTemperature(31)
                .weatherDescription(weatherDesc)
                .build();

        dao.createWeatherEntity(weather);
        assertNotNull(weather.getId());
        dao.deleteWeatherEntity(weather.getId());
        assertNull(dao.readWeatherEntityById(weather.getId()));
    }

}