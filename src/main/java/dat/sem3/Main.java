package dat.sem3;

import dat.sem3.dao.WeatherDAO;
import dat.sem3.dto.WeatherDTO;
import dat.sem3.model.WeatherEntity;
import dat.sem3.webscraping.WeatherScraper;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        WeatherApiReader.getCurrentWeather();
         */

        try {

            List<WeatherDTO> list = WeatherScraper.getInstance().scrape();

            list.forEach(weatherDTO -> {
                WeatherDAO dao = WeatherDAO.getInstance();
                WeatherEntity entity = weatherDTO.convertToEntity();
                if (dao.isWeatherAlreadyPersisted(entity)) {
                    dao.updateWeatherEntity(entity);
                } else {
                    dao.createWeatherEntity(entity);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);

    }
}
