package MTZ.mountainz.domain.mountain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MTZ.mountainz.domain.mountain.entity.Mountain;

@Repository
public interface MountainRepository extends JpaRepository<Mountain, Long> {

	List<Mountain> findByMountainRegion(String mountainRegion);

	List<Mountain> findByMountainLevel(String mountainLevel);

	List<Mountain> findByMountainSeason(String mountainSeason);
}
