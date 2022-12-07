package MTZ.mountainz.domain.badge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MTZ.mountainz.domain.badge.entity.MemberBadge;

@Repository
public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

	List<MemberBadge> findAllByMemberId(Long memberId);

	Optional<MemberBadge> findByBadgeIdAndMemberId(Long badgeId, Long memberId);
}
