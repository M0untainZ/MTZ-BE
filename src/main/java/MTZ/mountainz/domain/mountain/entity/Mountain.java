package MTZ.mountainz.domain.mountain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.mountain.dto.request.MountainRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mountain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mountain_id", nullable = false)
	private Long id;

	@Column(name = "mountainName", nullable = false)
	private String mountainName;

	@Column(name = "mountainRegion", nullable = false)
	private String mountainRegion;

	@Column(name = "mountainLevel", nullable = false)
	private String mountainLevel;

	@Column(name = "mountainSeason", nullable = false)
	private String mountainSeason;

	@Column(name = "mountainTime", nullable = false)
	private String mountainTime;

	@Column(name = "mountainQuiz")
	private String mountainQuiz;

	@Column(name = "mountainImg", nullable = false)
	private String mountainImg;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member member;

	public Mountain(Long mountainId) {
		this.id = mountainId;
	}

	public Mountain(MountainRequestDto mountainRequestDto, Member member) {
		this.mountainName = mountainRequestDto.getMountainName();
		this.mountainRegion = mountainRequestDto.getMountainRegion();
		this.mountainLevel = mountainRequestDto.getMountainLevel();
		this.mountainSeason = mountainRequestDto.getMountainSeason();
		this.mountainTime = mountainRequestDto.getMountainTime();
		this.mountainQuiz = mountainRequestDto.getMountainQuiz();
		this.mountainImg = mountainRequestDto.getMountainImg();
		this.member = member;
	}
}
