package MTZ.mountainz.domain.mountain.dto.response;

import MTZ.mountainz.domain.mountain.entity.Mountain;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MountainListDto {
	@ApiModelProperty(example = "산 아이디")
	private Long id;
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

	public static MountainListDto of(Mountain mountain) {
		return MountainListDto.builder()
				.id(mountain.getId())
				.name(mountain.getName())
				.region(mountain.getRegion())
				.level(mountain.getLevel())
				.season(mountain.getSeason())
				.img(mountain.getImg())
				.time(mountain.getTime())
				.build();
	}
}
