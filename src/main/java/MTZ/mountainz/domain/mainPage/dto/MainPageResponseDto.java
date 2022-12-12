package MTZ.mountainz.domain.mainPage.dto;

import MTZ.mountainz.domain.detailPageTwo.dto.response.CertificationResponseDto;
import MTZ.mountainz.domain.tag.dto.TagResponseDto;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "랭킹 순위")
    private List<String> topMembers;
    @ApiModelProperty(example = "랭커 사진")
    private List<String> topMembersPhoto;
    @ApiModelProperty(example = "인증 사진")
    private List<CertificationResponseDto> certificationPhoto;
    @ApiModelProperty(example = "태그 목록")
    private List<TagResponseDto> tagList;
    @ApiModelProperty(example = "랭커 포인트")
    private List<Integer> topMembersCertificationPoint;
}
