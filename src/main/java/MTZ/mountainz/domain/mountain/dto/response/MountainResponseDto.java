package MTZ.mountainz.domain.mountain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MountainResponseDto {
    private String mountainName;
    private String mountainRegion;
    private String mountainLevel;
    private String mountainSeason;
    private String mountainTime;
    private String mountainQuiz;
    private String mountainImg;
}