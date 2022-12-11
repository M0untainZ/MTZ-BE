package MTZ.mountainz.domain.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import MTZ.mountainz.domain.member.dto.request.MemberRequestDto;
import MTZ.mountainz.domain.myPage.dto.MyPageRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

	@Column(name = "region", nullable = false)
	private String region;

	@Column(name = "certificationPoint")
	private int certificationPoint;

	@Column(name = "badgeName")
	private String badgeName = "\uD83D\uDCAA Lv. 1 산을 알아가는 뉴비";

	@Column(name = "profilePhoto")
	private String profilePhoto = "https://mypengbucket.s3.ap-northeast-2.amazonaws.com/01_loginBadge.png";

	@Column(unique = true)
	private String kakaoId;

	@Enumerated(EnumType.STRING)
	private Authority authority;

	public Member(MemberRequestDto memberRequestDto, Authority authority) {
		this.email = memberRequestDto.getEmail();
		this.password = memberRequestDto.getPassword();
		this.nickName = memberRequestDto.getNickName();
		this.region = memberRequestDto.getRegion();
		this.authority = authority;
		this.kakaoId = null;
	}

	public Member(String kakaoId, String password, String nickName, String region, Authority authority) {
		this.email = kakaoId;
		this.kakaoId = kakaoId;
		this.password = password;
		this.nickName = nickName;
		this.region = region;
		this.authority = authority;

	}

	public Member(String email) {
		this.email = email;
	}

	public void updateCertificationPoint(int certificationPoint) {
		this.certificationPoint += certificationPoint;
	}

	public void deleteCertificationPoint(int certificationPoint) {
		this.certificationPoint -= certificationPoint;
	}

	public void update(MyPageRequestDto myPageRequestDto) {
		this.profilePhoto = myPageRequestDto.getProfilePhoto();
		this.nickName = myPageRequestDto.getNickName();
		this.region = myPageRequestDto.getRegion();
		this.badgeName = myPageRequestDto.getBadgeName();
	}
}


