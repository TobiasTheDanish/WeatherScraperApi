package dat.sem3;

import dat.sem3.dao.WeatherDAO;
import dat.sem3.dto.WeatherDTO;
import dat.sem3.model.WeatherEntity;
import dat.sem3.webscraping.WeatherScraper;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WeatherDAO dao = WeatherDAO.getInstance();
        dao.getWeatherFromTodayAnd6DaysAhead();
/*
        try {
            List<WeatherDTO> list = WeatherScraper.getInstance().scrape();

            list.forEach(weatherDTO -> {
                WeatherDAO dao = WeatherDAO.getInstance();
                WeatherEntity entity = dao.createWeatherEntity(weatherDTO.convertToEntity());
                System.out.println("Entity id: " + entity.getId());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

 */
    }
}
