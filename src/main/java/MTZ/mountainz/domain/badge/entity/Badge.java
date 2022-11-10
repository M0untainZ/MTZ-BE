package MTZ.mountainz.domain.badge.entity;


import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Badge {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id", nullable = false)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(name = "badgeContent", nullable = false)
    private String badgeContent;


    public Badge(Long badgeId) {
        this.id = badgeId;
    }

    public Badge(Member member){
        this.member = member;
        this.badgeContent = badgeContent;
    }
}


