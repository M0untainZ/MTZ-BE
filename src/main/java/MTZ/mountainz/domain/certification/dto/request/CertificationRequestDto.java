package MTZ.mountainz.domain.certification.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationRequestDto {
    private Long certificationId;
    private String photo;
}
