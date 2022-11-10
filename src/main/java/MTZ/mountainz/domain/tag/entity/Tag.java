package MTZ.mountainz.domain.tag.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false)
    private Long id;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    @Column(name = "tag_img", nullable = false)
    private String tagImg;

    public Tag(Long tagId) {
        this.id = tagId;}
}
