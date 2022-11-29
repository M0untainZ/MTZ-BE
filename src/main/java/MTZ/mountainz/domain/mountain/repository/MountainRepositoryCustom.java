package MTZ.mountainz.domain.mountain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import MTZ.mountainz.domain.detailPageOne.dto.request.FilterRequestDto;
import MTZ.mountainz.domain.mountain.entity.Mountain;

public interface MountainRepositoryCustom {

	Page<Mountain> findByMountainAll(Pageable pageable);

	List<Mountain> findByKeyword(String keyword);

	List<Mountain> findByMountainFilter(FilterRequestDto filterRequestDto);

}
