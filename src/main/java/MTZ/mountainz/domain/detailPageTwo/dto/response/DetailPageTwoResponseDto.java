package MTZ.mountainz.domain.detailPageTwo.dto.response;

import java.util.List;

import MTZ.mountainz.domain.badge.entity.Badge;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailPageTwoResponseDto {

	@ApiModelProperty(example = "산 이름")
	private String name;
	@ApiModelProperty(example = "산 사진")
	private String img;
	@ApiModelProperty(example = "산 지역")
	private String region;
	@ApiModelProperty(example = "산 난이도")
	private String level;
	@ApiModelProperty(example = "산 계절")
	private String season;
	@ApiModelProperty(example = "산 등반 시간")
	private String time;
	@ApiModelProperty(example = "인증 산 목록")
	private List<CertificationResponseDto> certificatedMountainList;
	@ApiModelProperty(example = "산 주소")
	private String juso;
	@ApiModelProperty(example = "내가 좋아요")
	private boolean correctLike;
	@ApiModelProperty(example = "좋아요 수")
	private Long countLike;
	@ApiModelProperty(example = "내가 인증")
	private boolean correctBadge;
	@ApiModelProperty(example = "인증 뱃지")
	private Badge badge;
//	@DateTimeFormat
//	private String openTime; // 뱃지 획득 시간

}
