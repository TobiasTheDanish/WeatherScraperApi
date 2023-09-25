package dat.sem3.model;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum WeatherDescription {
    CLEAR_SKY("Clear sky"),
    FAIR("Fair"),
    PARTLY_CLOUDY("Partly cloudy"),
    FOG("Fog"),
    CLOUDY("Cloudy"),
    LIGHT_RAIN("Light rain"),
    RAIN("Rain"),
    HEAVY_RAIN("Heavy rain"),
    LIGHT_SLEET("Light sleet"),
    SLEET("Sleet"),
    HEAVY_SLEET("Heavy sleet"),
    LIGHT_SNOW("Light snow"),
    SNOW("Snow"),
    HEAVY_SNOW("Heavy snow"),
    LIGHT_RAIN_SHOWERS("Light rain showers"),
    RAIN_SHOWERS("Rain showers"),
    HEAVY_RAIN_SHOWERS("Heavy rain showers"),
    LIGHT_SLEET_SHOWERS("Light sleet showers"),
    SLEET_SHOWERS("Sleet showers"),
    HEAVY_SLEET_SHOWERS("Heavy sleet showers"),
    LIGHT_SNOW_SHOWERS("Light snow showers"),
    SNOW_SHOWERS("Snow showers"),
    HEAVY_SNOW_SHOWERS("Heavy snow showers"),
    LIGHT_RAIN_THUNDER("Light rain and thunder"),
    RAIN_THUNDER("Rain and thunder"),
    HEAVY_RAIN_THUNDER("Heavy rain and thunder"),
    LIGHT_SLEET_THUNDER("Light sleet and thunder"),
    SLEET_THUNDER("Sleet and thunder"),
    HEAVY_SLEET_THUNDER("Heavy sleet and thunder"),
    LIGHT_SNOW_THUNDER("Light snow and thunder"),
    SNOW_THUNDER("Snow and thunder"),
    HEAVY_SNOW_THUNDER("Heavy snow and thunder");

    private final String description;

    WeatherDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public WeatherDescription get(String description) throws NoSuchElementException {
        return Arrays.stream(WeatherDescription.values())
                .filter(e -> e.description.equals(description))
                .findFirst()
                .orElseThrow();
    }
}
