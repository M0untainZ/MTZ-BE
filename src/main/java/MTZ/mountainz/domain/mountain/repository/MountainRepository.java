package MTZ.mountainz.domain.mountain.repository;

import MTZ.mountainz.domain.mountain.entity.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MountainRepository extends JpaRepository<Mountain, Long> {
}
