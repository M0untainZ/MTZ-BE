package MTZ.mountainz.domain.mountain.repository;

import java.util.List;

import MTZ.mountainz.domain.detailPageOne.dto.request.FilterRequestDto;
import MTZ.mountainz.domain.mountain.entity.Mountain;

public interface MountainRepositoryCustom {
	List<Mountain> findByKeyword(String keyword);

	List<Mountain> findByMountainFilter(FilterRequestDto filterRequestDto);

}
