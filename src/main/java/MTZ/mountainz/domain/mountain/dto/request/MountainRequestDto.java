package MTZ.mountainz.domain.mountain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MountainRequestDto {
    private String mountainName;
    private String mountainRegion;
    private String mountainLevel;
    private String mountainSeason;
    private String mountainTime;
    private String mountainQuiz;
    private String mountainImg;
}
