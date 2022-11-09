package MTZ.mountainz.domain.like.likeResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikesResponseDto {
    private Long countLike;
    private boolean correctLike;
}
