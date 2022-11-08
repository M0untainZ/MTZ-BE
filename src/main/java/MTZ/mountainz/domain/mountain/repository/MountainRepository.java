package MTZ.mountainz.domain.mountain.repository;

import MTZ.mountainz.domain.mountain.entity.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MountainRepository extends JpaRepository<Mountain, Long> {
}
