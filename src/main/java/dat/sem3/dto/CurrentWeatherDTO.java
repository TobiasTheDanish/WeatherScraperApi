package dat.sem3.dto;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import dat.sem3.model.WeatherDescription;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class CurrentWeatherDTO {

    @SerializedName(value = "LocationName")
    private final String location;
    @SerializedName(value = "CurrentData")
    private final CurrentData currentData;

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    public class CurrentData{
        @SerializedName(value = "temperature")
        private final int temperature;
        @SerializedName(value = "skyText")
        private final String weatherDescription;
        @SerializedName(value = "humidity")
        private final int humidity;
        @SerializedName(value = "windText")
        private final String wind;


    }
}
