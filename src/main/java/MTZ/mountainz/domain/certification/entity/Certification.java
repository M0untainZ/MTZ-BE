package MTZ.mountainz.domain.certification.entity;

import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id", nullable = false)
    private Long id;

    @Column(name = "photo", nullable = false)
    private String photo;

    @JoinColumn(name = "mountain_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Mountain mountain;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    public Certification(String url, Mountain mountain, Member member) {
        this.photo = url;
        this.mountain = mountain;
        this.member = member;
    }
}
