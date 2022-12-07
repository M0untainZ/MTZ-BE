package MTZ.mountainz.domain.member.dto.response;

import MTZ.mountainz.domain.badge.entity.Badge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseDto {
	private Badge badge;
	private boolean correctBadge;
}
