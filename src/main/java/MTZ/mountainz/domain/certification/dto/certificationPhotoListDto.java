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
public class certificationPhotoListDto {
    private String photo;

    public static certificationPhotoListDto of(Certification certification) {
        return certificationPhotoListDto.builder()
                .photo(certification.getPhoto())
                .build();
    }
}
