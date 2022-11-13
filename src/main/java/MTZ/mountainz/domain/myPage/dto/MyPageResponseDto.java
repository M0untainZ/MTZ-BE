package MTZ.mountainz.domain.myPage.dto;

import java.util.List;

import MTZ.mountainz.domain.badge.entity.Badge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MyPageResponseDto {

	String profilePhoto;
	String nickName;
	String region;
	List<Badge> badgeList;
}
