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
	private String mountainName;
	private String mountainRegion;
	private String mountainLevel;
	private String mountainSeason;
	private String mountainImg;

	public static MountainListDto of(Mountain mountain) {
		return MountainListDto.builder()
			.id(mountain.getId())
			.mountainName(mountain.getMountainName())
			.mountainRegion(mountain.getMountainRegion())
			.mountainLevel(mountain.getMountainLevel())
			.mountainSeason(mountain.getMountainSeason())
			.mountainImg(mountain.getMountainImg())
			.build();
	}
}
