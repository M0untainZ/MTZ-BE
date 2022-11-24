package MTZ.mountainz.domain.detailPageOne.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailPageOneResponseDto {
	private String name;
	private String quiz;
	private String img;
	private Long mountainLikeTotal;
	// 지역, 계절, 난이도, 소요시간
	private String region;
	private String season;
	private String level;
	private String time;
}
