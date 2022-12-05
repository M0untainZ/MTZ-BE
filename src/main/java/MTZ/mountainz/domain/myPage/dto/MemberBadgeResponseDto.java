package MTZ.mountainz.domain.myPage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberBadgeResponseDto {
	Long id;
	String badgeName;
	String content;
	String img;
	String openTime;
}
