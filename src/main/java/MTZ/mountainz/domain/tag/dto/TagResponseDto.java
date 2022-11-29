package MTZ.mountainz.domain.tag.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseDto {

	@ApiModelProperty(example = "태그 이름")
	private String name;
	@ApiModelProperty(example = "태그 사진")
	private String img;
}
