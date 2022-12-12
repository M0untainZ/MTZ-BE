package MTZ.mountainz.domain.certification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MTZ.mountainz.domain.certification.entity.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long>, CertificationRepositoryCustom {
	List<Certification> findAllByMountainId(Long mountainId);
}
