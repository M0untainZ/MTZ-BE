package MTZ.mountainz.domain.member.entity;

import MTZ.mountainz.domain.member.dto.request.MemberRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String memberRegion;

    @Column(nullable = false)
    private int certificationPoint;

    @Column(nullable = false)
    private int quizPoint;

    @Column(nullable = false)
    private String badgeName;


    public Member(MemberRequest memberRequest){
        this.email = memberRequest.getEmail();
        this.password = memberRequest.getPassword();
        this.nickName = memberRequest.getNickName();
        this.memberRegion = memberRequest.getMemberRegion();
    }
 }
