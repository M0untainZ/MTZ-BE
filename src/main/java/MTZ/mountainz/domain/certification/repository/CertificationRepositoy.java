package MTZ.mountainz.domain.certification.repository;

import MTZ.mountainz.domain.certification.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepositoy extends JpaRepository<Certification, Long> {
    List<Certification> findAllByMountainId(Long mountainId);
}
