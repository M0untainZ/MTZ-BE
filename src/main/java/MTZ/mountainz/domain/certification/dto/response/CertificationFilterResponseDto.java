package MTZ.mountainz.domain.certification.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationFilterResponseDto {

    @ApiModelProperty(example = "인증 사진")
    private String photo;

    @ApiModelProperty(example = "닉네임")
    private String nickName;

    @ApiModelProperty(example = "산 이름")
    private String name;

    @ApiModelProperty(example = "산 지역")
    private String region;
}
