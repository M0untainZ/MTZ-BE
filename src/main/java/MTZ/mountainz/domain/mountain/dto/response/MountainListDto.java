package MTZ.mountainz.domain.mountain.dto.response;

import MTZ.mountainz.domain.mountain.entity.Mountain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MountainListDto {
	private Long id;
	private String name;
	private String region;
	private String level;
	private String season;
	private String img;

	public static MountainListDto of(Mountain mountain) {
		return MountainListDto.builder()
			.id(mountain.getId())
			.name(mountain.getName())
			.region(mountain.getRegion())
			.level(mountain.getLevel())
			.season(mountain.getSeason())
			.img(mountain.getImg())
			.build();
	}
}
