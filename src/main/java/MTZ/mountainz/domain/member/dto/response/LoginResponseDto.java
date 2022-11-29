package MTZ.mountainz.domain.member.dto.response;

import MTZ.mountainz.domain.member.entity.Authority;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

	@ApiModelProperty(example = "닉네임")
	private String nickName;
	@ApiModelProperty(example = "뱃지 이름")
	private String badgeName;
	@ApiModelProperty(example = "유저 지역")
	private String region;
	@ApiModelProperty(example = "유저 권한")
	private Authority authority;
}
