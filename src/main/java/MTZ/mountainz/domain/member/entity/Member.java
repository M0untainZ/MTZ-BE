package MTZ.mountainz.domain.member.entity;

import MTZ.mountainz.domain.member.dto.request.MemberRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickName", nullable = false)
    private String nickName;

    @Column(name = "memberRegion", nullable = false)
    private String memberRegion;

    @Column(name = "certificationPoint")
    private int certificationPoint;

    @Column(name = "quizPoint")
    private int quizPoint;

    @Column(name = "badgeName")
    private String badgeName;

    public Member(MemberRequestDto memberRequestDto){
        this.email = memberRequestDto.getEmail();
        this.password = memberRequestDto.getPassword();
        this.nickName = memberRequestDto.getNickName();
        this.memberRegion = memberRequestDto.getMemberRegion();
    }
    public void updateCertificationPoint(int certificationPoint) {
        this.certificationPoint =certificationPoint;
    }
 }
