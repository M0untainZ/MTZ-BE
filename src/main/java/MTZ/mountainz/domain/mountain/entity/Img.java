package MTZ.mountainz.domain.mountain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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

    public Img(String url, Mountain mountain) {
        this.url = url;
        this.mountain = mountain;
    }
}
