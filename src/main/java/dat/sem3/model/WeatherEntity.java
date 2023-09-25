package dat.sem3.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "weather")
public class WeatherEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "date", nullable = false)
        private LocalDate date;

        @Column(name = "temperature", nullable = false)
        private int temperature;

        @Column(name = "wind", nullable = false)
        private int wind;

        @Column(name = "rain", nullable = false)
        private double rain;

        @Column(name = "min_temperature", nullable = false)
        private int minTemperature;

        @Column(name = "max_temperature", nullable = false)
        private int maxTemperature;

        @OneToOne
        private WeatherDescriptionEntity weatherDescription;

}
