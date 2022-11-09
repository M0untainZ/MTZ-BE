package MTZ.mountainz.domain.certification.dto.response;

import MTZ.mountainz.domain.certification.entity.Certification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationPhotoListResponseDto {
    private String photo;
    private String nickName;
}
