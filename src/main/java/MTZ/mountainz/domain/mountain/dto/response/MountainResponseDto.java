package MTZ.mountainz.domain.mountain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MountainResponseDto {
	private String name;
	private String region;
	private String level;
	private String season;
	private String time;
	private String quiz;
	private String img;
}
