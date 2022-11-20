package MTZ.mountainz.domain.member.dto.response;

import MTZ.mountainz.domain.member.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
	private String nickName;
	private String badgeName;
	private String region;
	private Authority authority;
}
