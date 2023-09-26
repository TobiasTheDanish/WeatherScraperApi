package dat.sem3.webscraping;

import dat.sem3.dto.TemperatureDTO;
import dat.sem3.dto.WeatherDTO;
import dat.sem3.model.WeatherDescription;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeatherScraper {
    private static WeatherScraper instance;
    private final String url;

    private WeatherScraper(String url) {
        this.url = url;
    }

    public static WeatherScraper getInstance(){
        if (instance == null) {
            instance = new WeatherScraper("https://www.yr.no/en/forecast/daily-table/2-2618425/Denmark/Capital%20Region/Copenhagen/Copenhagen");
        }

        return instance;
    }

    public List<WeatherDTO> scrape() throws IOException {
        List<WeatherDTO> res = new ArrayList<>();
        Document dom = Jsoup.connect(url).get();
        Elements days = dom.select(".daily-weather-list-item");

        days.forEach(day -> {
            res.add(getWeatherDTO(day));
        });

        return res;
    }

    protected WeatherDTO getWeatherDTO(Element day) {
        LocalDate date = scrapeDate(day.select("div.daily-weather-list-item__date-and-warnings > a > h3 > time").get(0));

        Elements symbols = day.select(".daily-weather-list-item__symbol");
        WeatherDescription[] descriptions = scrapeDescriptions(symbols);

        TemperatureDTO minMax = scrapeTemperatures(day.select("div.daily-weather-list-item__forecast > div.daily-weather-list-item__temperature > span.min-max-temperature").get(0));

        int wind = Integer.parseInt(day.select("div.daily-weather-list-item__forecast > div.daily-weather-list-item__wind > span.wind.daily-weather-list-item__wind-value > span > span.wind__value").text());
        double rain = Double.parseDouble(day.select("div.daily-weather-list-item__forecast > div.daily-weather-list-item__precipitation > span > span:nth-child(2)").text());

        return WeatherDTO.builder()
                .date(date)
                .minTemperature(minMax.getMin())
                .maxTemperature(minMax.getMax())
                .wind(wind)
                .rain(rain)
                .night(descriptions[0])
                .morning(descriptions[1])
                .afternoon(descriptions[2])
                .evening(descriptions[3])
                .build();
    }

    protected LocalDate scrapeDate(Element element) {
        return LocalDate.parse(element.attr("datetime"));
    }

    protected WeatherDescription[] scrapeDescriptions(Elements symbols) {
        WeatherDescription[] descriptions = new WeatherDescription[4];
        for (int i = 0; i < symbols.size(); i++) {
            String symbolText = symbols.get(i).select("div > img").attr("alt");

            if (symbolText.contains(": ")) {
                symbolText = symbolText.split(": ")[1];
            }
            descriptions[i] = WeatherDescription.get(symbolText);
        }

        return descriptions;
    }

    protected TemperatureDTO scrapeTemperatures(Element minMax) {
        int maxTemp = Integer.parseInt(minMax.select("span.temperature.min-max-temperature__max").text().replace("°", ""));
        int minTemp = Integer.parseInt(minMax.select("span.temperature.min-max-temperature__min").text().replace("°", ""));
        return new TemperatureDTO(maxTemp, minTemp);
    }
}
