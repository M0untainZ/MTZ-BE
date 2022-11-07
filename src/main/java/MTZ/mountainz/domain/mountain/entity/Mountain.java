package MTZ.mountainz.domain.mountain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mountain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mountainRegion", nullable = false)
    private String mountainRegion;

    @Column(name = "mountainLevel", nullable = false)
    private String mountainLevel;

    @Column(name = "mountainSeason", nullable = false)
    private String mountainSeason;

    @Column(name = "mountainTime", nullable = false)
    private String mountainTime;

    @Column(name = "mountainQuiz")
    private String mountainQuiz;

    @Column(name = "mountainImg", nullable = false)
    private String mountainImg;
}
