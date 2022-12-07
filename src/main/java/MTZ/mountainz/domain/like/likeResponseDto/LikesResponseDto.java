package MTZ.mountainz.domain.like.likeResponseDto;

import MTZ.mountainz.domain.badge.entity.Badge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikesResponseDto {
	private boolean correctLike;
	private Long countLike;
	private Badge badge;
	private boolean correctBadge;
}
