package MTZ.mountainz.domain.certification.repository;

import MTZ.mountainz.domain.certification.dto.request.PhotoFilterRequestDto;
import MTZ.mountainz.domain.certification.dto.response.CertificationFilterResponseDto;
import MTZ.mountainz.domain.certification.entity.Certification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CertificationRepositoryCustom {

    Page<Certification> findByCertificationAll(Pageable pageable);

    List<CertificationFilterResponseDto> findByPhotosFilter(PhotoFilterRequestDto photoFilterRequestDto);
}
