package dat.sem3.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
