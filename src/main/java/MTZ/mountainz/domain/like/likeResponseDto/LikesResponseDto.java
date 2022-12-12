package MTZ.mountainz.domain.like.likeResponseDto;

import MTZ.mountainz.domain.badge.entity.Badge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikesResponseDto {
	private boolean correctLike;
	private Long countLike;
	private Badge badge;
	private boolean correctBadge;
	@DateTimeFormat
	private String openTime;

}
