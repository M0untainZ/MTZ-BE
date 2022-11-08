package MTZ.mountainz.domain.mountain.repository;

import MTZ.mountainz.domain.mountain.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends JpaRepository<Img, Long> {

}
