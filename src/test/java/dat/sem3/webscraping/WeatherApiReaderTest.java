package dat.sem3.webscraping;

import dat.sem3.dto.CurrentWeatherDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherApiReaderTest {

    @Test
    public void GetCurrentWeather() {
        CurrentWeatherDTO currentWeather = WeatherApiReader.getCurrentWeather();
        assertNotNull(currentWeather);
        assertNotNull(currentWeather.getLocation());
        assertNotNull(currentWeather.getCurrentData());
        assertNotNull(currentWeather.getCurrentData().getTemperature());
        assertNotNull(currentWeather.getCurrentData().getWeatherDescription());
        assertNotNull(currentWeather.getCurrentData().getHumidity());
        assertNotNull(currentWeather.getCurrentData().getWind());
    }
}