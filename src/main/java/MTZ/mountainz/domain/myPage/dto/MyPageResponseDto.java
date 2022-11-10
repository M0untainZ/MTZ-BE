package MTZ.mountainz.domain.myPage.dto;


import MTZ.mountainz.domain.badge.entity.Badge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MyPageResponseDto {

    String profilePhoto;
    String nickName;
    String MemberRegion;
    List<Badge> memberBadgeList;
}
