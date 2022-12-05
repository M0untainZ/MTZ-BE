package MTZ.mountainz.domain.badge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MTZ.mountainz.domain.badge.entity.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
