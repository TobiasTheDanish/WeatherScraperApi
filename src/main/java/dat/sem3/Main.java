package dat.sem3;

import dat.sem3.webscraping.WeatherApiReader;

public class Main {
    public static void main(String[] args) {
        WeatherApiReader.getCurrentWeather();
        /*
        try {

            List<WeatherDTO> list = WeatherScraper.getInstance().scrape();
            //WeatherApiReader.ApiReader();

            list.forEach(weatherDTO -> {
                WeatherDAO dao = WeatherDAO.getInstance();
                WeatherEntity entity = dao.createWeatherEntity(weatherDTO.convertToEntity());
                //System.out.println("Entity id: " + entity.getId());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);

         */
    }
}
