package MTZ.mountainz.domain.member.repository;

import MTZ.mountainz.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickName(String nickname);

    List<Member> findByCertificationPointGreaterThanOrderByCertificationPointDesc(int i);
}
