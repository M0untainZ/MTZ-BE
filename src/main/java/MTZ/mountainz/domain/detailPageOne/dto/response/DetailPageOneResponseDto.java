package MTZ.mountainz.domain.detailPageOne.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailPageOneResponseDto {

	@ApiModelProperty(example = "산 아이디")
	private Long id;

	@ApiModelProperty(example = "산 이름")
	private String name;

	@ApiModelProperty(example = "산 사진")
	private String img;

	@ApiModelProperty(example = "산 총 좋아요")
	private Long mountainLikeTotal;

	@ApiModelProperty(example = "산 지역")
	private String region;

	@ApiModelProperty(example = "산 계절")
	private String season;

	@ApiModelProperty(example = "산 난이드")
	private String level;

	@ApiModelProperty(example = "산 등반 시간")
	private String time;
}
