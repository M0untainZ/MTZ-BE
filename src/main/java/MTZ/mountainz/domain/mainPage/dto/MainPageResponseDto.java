package MTZ.mountainz.domain.mainPage.dto;

import MTZ.mountainz.domain.certification.entity.Certification;
import MTZ.mountainz.domain.detailPageTwo.dto.response.CertificationResponseDto;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import MTZ.mountainz.domain.tag.dto.TagResponseDto;
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

    private List<String> topMembers;
    private List<CertificationResponseDto> certificationPhoto;
    private List<TagResponseDto> tagList;

}
