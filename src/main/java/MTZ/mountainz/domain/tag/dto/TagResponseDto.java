package MTZ.mountainz.domain.tag.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseDto {
    private String tag;
    private String tagImg;
}
