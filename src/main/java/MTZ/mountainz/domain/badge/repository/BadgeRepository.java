package MTZ.mountainz.domain.badge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MTZ.mountainz.domain.badge.entity.Badge;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

	List<Badge> findAllByMemberId(Long memberId);
}
