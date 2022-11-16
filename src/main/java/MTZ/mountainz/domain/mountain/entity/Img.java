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

import MTZ.mountainz.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Img {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "img_id", nullable = false)
	private Long id;

	@Column(name = "img_url", nullable = false)
	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mountain_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Mountain mountain;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member member;

	public Img(String url, Mountain mountain) {
		this.url = url;
		this.mountain = mountain;
	}

	public Img(String url, Mountain mountain, Member member) {
		this.url = url;
		this.mountain = mountain;
		this.member = member;
	}
}
