package MTZ.mountainz.domain.mountain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MountainRequestDto {
	private Long id;
	private String mountainName;
	private String mountainRegion;
	private String mountainLevel;
	private String mountainSeason;
	private String mountainTime;
	private String mountainQuiz;
	private String mountainImg;
}
