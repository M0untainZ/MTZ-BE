package MTZ.mountainz.domain.mountain.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MountainResponseDto {

	@ApiModelProperty(example = "산 이름")
	private String name;
	@ApiModelProperty(example = "산 지역")
	private String region;
	@ApiModelProperty(example = "산 난이도")
	private String level;
	@ApiModelProperty(example = "산 계절")
	private String season;
	@ApiModelProperty(example = "산 이미지")
	private String img;
	@ApiModelProperty(example = "산 등반 시간")
	private String time;

}
