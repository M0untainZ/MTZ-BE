package MTZ.mountainz.domain.member.entity;

import MTZ.mountainz.domain.member.dto.request.MemberRequestDto;
import MTZ.mountainz.domain.myPage.dto.MyPageRequestDto;
import MTZ.mountainz.domain.myPage.dto.MyPageResponseDto;
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
    @Column(name = "member_id", nullable = false)
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

    @Column(name = "profilePhoto")
    private String profilePhoto;

    public Member(MemberRequestDto memberRequestDto){
        this.email = memberRequestDto.getEmail();
        this.password = memberRequestDto.getPassword();
        this.nickName = memberRequestDto.getNickName();
        this.memberRegion = memberRequestDto.getMemberRegion();
    }
    public void updateCertificationPoint(int certificationPoint) {
        this.certificationPoint += certificationPoint;
    }


    public void update(MyPageRequestDto myPageRequestDto) {
        this.profilePhoto = myPageRequestDto.getProfilePhoto();
        this.nickName = myPageRequestDto.getNickName();
        this.memberRegion = myPageRequestDto.getMemberRegion();
        this.badgeName = myPageRequestDto.getMemberBadgeName();
    }
 }


