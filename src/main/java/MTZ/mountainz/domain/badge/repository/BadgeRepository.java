package MTZ.mountainz.domain.badge.repository;

import MTZ.mountainz.domain.badge.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    List<Badge> findAllBymemberId(Long memberId);
}
