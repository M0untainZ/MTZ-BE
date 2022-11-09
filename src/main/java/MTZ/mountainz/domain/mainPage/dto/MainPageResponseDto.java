package MTZ.mountainz.domain.mainPage.dto;

import MTZ.mountainz.domain.certification.entity.Certification;
import MTZ.mountainz.domain.detailPageTwo.dto.response.CertificationResponseDto;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MainPageResponseDto {
    //member
    private Long id;
    private String email;
    private String nickName;
    private String memberRegion;
    private String badgeName;
    //mountain
    private String mountainRegion;
    private String mountainLevel;
    private String mountainSeason;
    private String mountainName;
    private String mountainImg;
    //certification
    private List<String> topMembers;
    private List<CertificationResponseDto> certificationPhoto;

//    public static MainPageResponseDto of(Member member, Mountain mountain, Certification certification) {
//        return MainPageResponseDto.builder()
//                .id(member.getId())
//                .email(member.getEmail())
//                .nickName(member.getNickName())
//                .memberRegion(member.getMemberRegion())
//                .badgeName(member.getBadgeName())
//                .mountainRegion(mountain.getMountainRegion())
//                .mountainLevel(mountain.getMountainLevel())
//                .mountainSeason(mountain.getMountainSeason())
//                .mountainName(mountain.getMountainName())
//                .mountainImg(mountain.getMountainImg())
//                .build();
//    }
}
