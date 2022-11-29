package MTZ.mountainz.domain.detailPageTwo.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationResponseDto {

    @ApiModelProperty(example = "인증 사진")
    private String photo;
}
