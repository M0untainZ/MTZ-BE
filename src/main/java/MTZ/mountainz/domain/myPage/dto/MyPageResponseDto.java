package MTZ.mountainz.domain.myPage.dto;

import java.util.List;

import MTZ.mountainz.domain.badge.entity.MemberBadge;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MyPageResponseDto {


	@ApiModelProperty(example = "프로필 사진")
	String profilePhoto;
	@ApiModelProperty(example = "닉네임")
	String nickName;
	@ApiModelProperty(example = "유저 지역")
	String region;
	@ApiModelProperty(example = "뱃지 목록")
	List<MemberBadge> badgeList;
}
