package MTZ.mountainz.domain.detailPageOne.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailPageOneResponseDto {
    private String mountainName;
    private String mountainQuiz;
    private String mountainImg;
    private Long mountainLikeTotal;
}
