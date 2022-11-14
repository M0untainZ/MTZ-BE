package MTZ.mountainz.domain.queryDSL;

import static MTZ.mountainz.domain.mountain.entity.QMountain.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import MTZ.mountainz.domain.detailPageOne.dto.request.FilterRequestDto;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import MTZ.mountainz.domain.mountain.repository.MountainRepositoryCustom;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MountainRepositoryImpl implements MountainRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Mountain> findByKeyword(String keyword) {

		List<Mountain> mountains = jpaQueryFactory.selectFrom(mountain)
			.where(mountain.name.contains(keyword))
			.fetch();
		return mountains;
	}

	@Override
	public List<Mountain> findByMountainFilter(FilterRequestDto filterRequestDto) {

		List<Mountain> mountains = jpaQueryFactory.selectFrom(mountain)
			.where(eqRegion(filterRequestDto.getRegion()),
				eqSeason(filterRequestDto.getSeason()),
				eqLevel(filterRequestDto.getLevel()),
				eqTime(filterRequestDto.getTime())
			)
			.fetch();

		return mountains;
	}

	// findByMountainFilter 메서드
	private BooleanExpression eqRegion(String region) {
		return region != null ? mountain.region.eq(region) : null;
	}

	private BooleanExpression eqSeason(String season) {
		return season != null ? mountain.season.eq(season) : null;
	}

	private BooleanExpression eqLevel(String level) {
		return level != null ? mountain.level.eq(level) : null;
	}

	private BooleanExpression eqTime(String time) {
		return time != null ? mountain.time.eq(time) : null;
	}
}
