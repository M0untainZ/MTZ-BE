package MTZ.mountainz.domain.mountain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MTZ.mountainz.domain.mountain.entity.Mountain;

@Repository
public interface MountainRepository extends JpaRepository<Mountain, Long> {

	List<Mountain> findByRegion(String region);

	List<Mountain> findByLevel(String level);

	List<Mountain> findBySeason(String season);

	List<Mountain> findByName(String name);

	List<Mountain> findByRegionAndSeasonAndLevelAndTime(
		String region, String season, String level, String time);
}
