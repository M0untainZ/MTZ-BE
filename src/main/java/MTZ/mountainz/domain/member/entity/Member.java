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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String memberRegion;

//    @JsonIgnore
//    @Column(nullable = false)
//    private int certificationPoint;
//
//    @JsonIgnore
//    @Column(nullable = false)
//    private int quizPoint;

//    @JsonIgnore
//    @Column(nullable = false)
//    private String badgeName;


    public Member(MemberRequestDto memberRequestDto){
        this.email = memberRequestDto.getEmail();
        this.password = memberRequestDto.getPassword();
        this.nickName = memberRequestDto.getNickName();
        this.memberRegion = memberRequestDto.getMemberRegion();
    }
 }
