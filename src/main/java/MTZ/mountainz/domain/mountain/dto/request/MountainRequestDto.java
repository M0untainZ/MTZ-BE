package MTZ.mountainz.domain.mountain.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MountainRequestDto {
    private final String mountainRegion;
    private final String mountainLevel;
    private final String mountainSeason;
    private final String mountainTime;
    private final String mountainQuiz;
    private final String mountainImg;
}
