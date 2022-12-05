package MTZ.mountainz.domain.mountain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MountainRequestDto {
	private Long id;
	private String name;
	private String region;
	private String level;
	private String season;
	private String time;
	private String img;
}
