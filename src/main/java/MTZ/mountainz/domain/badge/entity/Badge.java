package MTZ.mountainz.domain.badge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Badge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "badge_id", nullable = false)
	private Long id;

	@Column(name = "badge_name", nullable = false)
	private String badgeName;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "img", nullable = false)
	private String img;

	public Badge(Long badgeId) {
		this.id = badgeId;
	}

	public Badge(String badgeName, String content, String img) {
		this.badgeName = badgeName;
		this.content = content;
		this.img = img;
	}
}


