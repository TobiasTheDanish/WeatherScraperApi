package dat.sem3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "weather_description")
public class WeatherDescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "night", nullable = false)
    private WeatherDescription night;
    @Column(name = "morning", nullable = false)
    private WeatherDescription morning;
    @Column(name = "afternoon", nullable = false)
    private WeatherDescription afternoon;
    @Column(name = "evening", nullable = false)
    private WeatherDescription evening;
}
