package MTZ.mountainz.domain.certification.dto;

import MTZ.mountainz.domain.certification.entity.Certification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CertificationPhotoListDto {
    private String photo;

    public static CertificationPhotoListDto of(Certification certification) {
        return CertificationPhotoListDto.builder()
                .photo(certification.getPhoto())
                .build();
    }
}
