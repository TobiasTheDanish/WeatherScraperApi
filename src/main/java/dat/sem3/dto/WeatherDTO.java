package dat.sem3.dto;

import dat.sem3.model.WeatherDescription;
import dat.sem3.model.WeatherDescriptionEntity;
import dat.sem3.model.WeatherEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class WeatherDTO {
    private final LocalDate date;
    //private final int temperature;
    private final int wind;
    private final double rain;
    private final int minTemperature;
    private final int maxTemperature;
    private final WeatherDescription night;
    private final WeatherDescription morning;
    private final WeatherDescription afternoon;
    private final WeatherDescription evening;

    public WeatherEntity convertToEntity() {
        WeatherDescriptionEntity weatherDescriptionEntity = WeatherDescriptionEntity.builder()
                .night(getNight())
                .morning(getMorning())
                .afternoon(getAfternoon())
                .evening(getEvening())
                .build();

        return WeatherEntity.builder()
                .date(getDate())
                .maxTemperature(getMaxTemperature())
                .minTemperature(getMinTemperature())
                .weatherDescription(weatherDescriptionEntity)
                .rain(getRain())
                .wind(getWind())
                .build();
    }
}
