package MTZ.mountainz.domain.certification.repository;

import MTZ.mountainz.domain.certification.dto.request.PhotoFilterRequestDto;
import MTZ.mountainz.domain.certification.dto.response.CertificationFilterResponseDto;
import MTZ.mountainz.domain.certification.entity.Certification;

import java.util.List;

public interface CertificationRepositoryCustom {

    List<CertificationFilterResponseDto> findByPhotosFilter(PhotoFilterRequestDto photoFilterRequestDto);
}
