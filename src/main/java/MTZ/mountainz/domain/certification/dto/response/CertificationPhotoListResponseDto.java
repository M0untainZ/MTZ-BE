package MTZ.mountainz.domain.certification.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationPhotoListResponseDto {
	private String photo;
	private String nickName;
}
