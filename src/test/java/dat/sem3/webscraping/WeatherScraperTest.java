package dat.sem3.webscraping;

import dat.sem3.dto.TemperatureDTO;
import dat.sem3.dto.WeatherDTO;
import dat.sem3.model.WeatherDescription;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WeatherScraperTest {
    static Document dom = null;
    Element day;
    @BeforeAll
    static void startUp() {
        try {
            File html = new File("./testTemplate.html");
            dom = Jsoup.parse(html);
        } catch (IOException e) {
            System.out.println("Error occurred when trying to read/parse html file.\nError message: " + e.getMessage() + "\n\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    @BeforeEach
    void setup() {
        assertNotNull(dom);

        Elements days = dom.select(".daily-weather-list-item");
        day = days.get(new Random().nextInt(days.size()));
    }

    @Test
    void getInstance() {
        WeatherScraper scraper = WeatherScraper.getInstance();
        assertNotNull(scraper);

        WeatherScraper scraper2 = WeatherScraper.getInstance();
        assertSame(scraper, scraper2);
    }

    @Test
    void scrape() throws IOException {
        List<WeatherDTO> list = WeatherScraper.getInstance().scrape();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        list.forEach(Assertions::assertNotNull);
    }

    @Test
    void getWeatherDTO() {
        WeatherDTO dto = WeatherScraper.getInstance().getWeatherDTO(day);
        assertNotNull(dto);
        assertTrue(dto.getDate().isAfter(LocalDate.of(2023, 9, 25)) || dto.getDate().isEqual(LocalDate.of(2023, 9, 25)));
        assertTrue(dto.getMaxTemperature() >= dto.getMinTemperature());
    }

    @Test
    void scrapeDate() {
        Element dateElement = day.select("div.daily-weather-list-item__date-and-warnings > a > h3 > time").get(0);

        assertDoesNotThrow(() -> {
            LocalDate date = WeatherScraper.getInstance().scrapeDate(dateElement);
            assertNotNull(date);
            assertTrue(
                    date.isAfter(LocalDate.of(2023, 9, 25))
                            || date.isEqual(LocalDate.of(2023, 9, 25))
            );
        });
    }

    @Test
    void scrapeDescriptions() {
        Elements descriptionElements = day.select(".daily-weather-list-item__symbol");
        WeatherDescription[] descriptions = WeatherScraper.getInstance().scrapeDescriptions(descriptionElements);

        assertNotNull(descriptions);
        assertEquals(4, descriptions.length);
        Arrays.stream(descriptions).forEach(Assertions::assertNotNull);
    }

    @Test
    void scrapeTemperatures() {
        Element temperatureElement = day.select("div.daily-weather-list-item__forecast > div.daily-weather-list-item__temperature > span.min-max-temperature").get(0);

        assertDoesNotThrow(() -> {
            TemperatureDTO temperatureDTO = WeatherScraper.getInstance().scrapeTemperatures(temperatureElement);
            assertNotNull(temperatureDTO);
            assertTrue(temperatureDTO.getMax() >= temperatureDTO.getMin());
        });
    }
}