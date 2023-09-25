package dat.sem3;

import dat.sem3.config.HibernateConfig;
import dat.sem3.webscraping.WeatherApiReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        WeatherApiReader.ApiReader();

    }
}
