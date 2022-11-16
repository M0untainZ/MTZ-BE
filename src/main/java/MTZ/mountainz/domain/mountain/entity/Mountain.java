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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mountain_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "region", nullable = false)
	private String region;

	@Column(name = "level", nullable = false)
	private String level;

	@Column(name = "season", nullable = false)
	private String season;

	@Column(name = "time", nullable = false)
	private String time;

	@Column(name = "quiz")
	private String quiz;

	@Column(name = "img", nullable = false)
	private String img;

	@Column(name = "latitude")
	private String latitude;    // 위도

	@Column(name = "longitude")
	private String longitude;    // 경도

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member member;

	public Mountain(Long mountainId) {
		this.id = mountainId;
	}

	public Mountain(MountainRequestDto mountainRequestDto, Member member) {
		this.name = mountainRequestDto.getName();
		this.region = mountainRequestDto.getRegion();
		this.level = mountainRequestDto.getLevel();
		this.season = mountainRequestDto.getSeason();
		this.time = mountainRequestDto.getTime();
		this.quiz = mountainRequestDto.getQuiz();
		this.img = mountainRequestDto.getImg();
		this.member = member;
	}
}
