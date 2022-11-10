package MTZ.mountainz.domain.tag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tag_id", nullable = false)
	private Long id;

	@Column(name = "tag_name", nullable = false)
	private String tagName;

	@Column(name = "tag_img", nullable = false)
	private String tagImg;

	public Tag(Long tagId) {
		this.id = tagId;
	}
}
