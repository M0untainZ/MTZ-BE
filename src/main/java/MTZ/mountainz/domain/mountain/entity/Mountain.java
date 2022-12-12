package MTZ.mountainz.domain.mountain.entity;

import MTZ.mountainz.domain.mountain.dto.request.MountainRequestDto;
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
    @Column(name = "mountain_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "season", nullable = false)
    private String season;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "img", nullable = false)
    private String img;

    @Column(name = "juso")
    private String juso;


    public Mountain(Long mountainId) {
        this.id = mountainId;
    }

    public Mountain(MountainRequestDto mountainRequestDto) {
        this.name = mountainRequestDto.getName();
        this.region = mountainRequestDto.getRegion();
        this.level = mountainRequestDto.getLevel();
        this.season = mountainRequestDto.getSeason();
        this.time = mountainRequestDto.getTime();
        this.img = mountainRequestDto.getImg();
    }
}
