package dat.sem3.webscraping;

import dat.sem3.dto.WeatherDTO;
import dat.sem3.model.WeatherDescription;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
            String dateTime = day.select("div.daily-weather-list-item__date-and-warnings > a > h3 > time").attr("datetime");
            LocalDate date = LocalDate.parse(dateTime);

            WeatherDescription[] descriptions = new WeatherDescription[4];
            Elements symbols = day.select(".daily-weather-list-item__symbol");
            for (int i = 0; i < symbols.size(); i++) {
                String symbolText = symbols.get(i).select("div > img").attr("alt");

                if (symbolText.contains(": ")) {
                    symbolText = symbolText.split(": ")[1];
                }

                descriptions[i] = WeatherDescription.get(symbolText);
            }

            int maxTemp = Integer.parseInt(day.select("div.daily-weather-list-item__forecast > div.daily-weather-list-item__temperature > span.min-max-temperature > span.temperature.min-max-temperature__max").text().replace("°", ""));
            int minTemp = Integer.parseInt(day.select("div.daily-weather-list-item__forecast > div.daily-weather-list-item__temperature > span.min-max-temperature > span.temperature.min-max-temperature__min").text().replace("°", ""));

            int wind = Integer.parseInt(day.select("div.daily-weather-list-item__forecast > div.daily-weather-list-item__wind > span.wind.daily-weather-list-item__wind-value > span > span.wind__value").text());
            double rain = Double.parseDouble(day.select("div.daily-weather-list-item__forecast > div.daily-weather-list-item__precipitation > span > span:nth-child(2)").text());

            res.add(WeatherDTO.builder()
                    .date(date)
                    .minTemperature(minTemp)
                    .maxTemperature(maxTemp)
                    .wind(wind)
                    .rain(rain)
                    .night(descriptions[0])
                    .morning(descriptions[1])
                    .afternoon(descriptions[2])
                    .evening(descriptions[3])
                    .build());
        });

        return res;
    }

}
