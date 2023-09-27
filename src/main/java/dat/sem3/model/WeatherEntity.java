package dat.sem3.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "weather")
public class WeatherEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "date", nullable = false)
        private LocalDate date;

        @Column(name = "wind", nullable = false)
        private int wind;

        @Column(name = "rain", nullable = false)
        private double rain;

        @Column(name = "min_temperature", nullable = false)
        private int minTemperature;

        @Column(name = "max_temperature", nullable = false)
        private int maxTemperature;

        @OneToOne(cascade = CascadeType.ALL)
        private WeatherDescriptionEntity weatherDescription;


        @Builder
        public WeatherEntity(LocalDate date, int wind, double rain, int minTemperature, int maxTemperature, WeatherDescriptionEntity weatherDescription) {
                this.date = date;
                this.wind = wind;
                this.rain = rain;
                this.minTemperature = minTemperature;
                this.maxTemperature = maxTemperature;
                this.weatherDescription = weatherDescription;
        }
}
