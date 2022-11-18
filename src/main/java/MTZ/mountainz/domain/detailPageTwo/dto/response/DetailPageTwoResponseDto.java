package MTZ.mountainz.domain.detailPageTwo.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailPageTwoResponseDto {
	private String name;
	private String img;
	private String region;
	private String level;
	private String season;
	private String time;
	private List<CertificationResponseDto> certificatedMountainList;
	private String latitude;
	private String longitude;
	private boolean correctLike;
	private Long countLike;
}
