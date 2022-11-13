package MTZ.mountainz.domain.detailPageOne.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequestDto {
	private String region;
	private String season;
	private String level;
	private String time;
}
