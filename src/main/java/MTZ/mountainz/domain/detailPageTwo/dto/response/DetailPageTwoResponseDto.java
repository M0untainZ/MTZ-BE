package MTZ.mountainz.domain.detailPageTwo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailPageTwoResponseDto {
    private String mountainName;
    private String mountainRegion;
    private String mountainLevel;
    private String mountainSeason;
    private String mountainTime;
    private String mountainQuiz;
    private List<CertificationResponseDto> certificatedMountainList;
}
