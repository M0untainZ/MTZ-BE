package MTZ.mountainz.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class KakaoUserInfoDto {
	private Long id;
	private String nickname;
}
