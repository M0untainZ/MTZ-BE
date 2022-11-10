package MTZ.mountainz.domain.mountain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MTZ.mountainz.domain.mountain.entity.Img;

@Repository
public interface ImgRepository extends JpaRepository<Img, Long> {

}
