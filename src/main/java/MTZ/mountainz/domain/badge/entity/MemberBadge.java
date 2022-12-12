package MTZ.mountainz.domain.badge.entity;

import MTZ.mountainz.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberBadge_id", nullable = false)
    private Long id;

    @JoinColumn(name = "badge_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Badge badge;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @DateTimeFormat
    private String openTime;

    public MemberBadge(Badge badge, Member member) {
        LocalDateTime date = LocalDateTime.now();
        this.openTime = date.format(DateTimeFormatter.ISO_DATE);
        this.badge = badge;
        this.member = member;
    }
}
