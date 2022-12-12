package MTZ.mountainz.domain.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MTZ.mountainz.domain.like.entity.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
	Optional<Likes> findByMountainIdAndMemberEmail(Long mountainId, String email);

	Long countAllByMountainId(Long mountainId);

	Long countAllByMemberId(Long memberId);
	
}
