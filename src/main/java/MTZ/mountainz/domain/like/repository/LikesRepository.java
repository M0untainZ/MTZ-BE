package MTZ.mountainz.domain.like.repository;

import MTZ.mountainz.domain.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByMountainIdAndMemberEmail(Long mountainId, String email);
    // 숫자 반환 산 아이디에 해당하는 like
    Long countAllByMountainId(Long mountainId);


}
