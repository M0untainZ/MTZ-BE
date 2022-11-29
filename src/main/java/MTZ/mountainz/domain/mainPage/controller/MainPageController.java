package MTZ.mountainz.domain.mainPage.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MTZ.mountainz.domain.mainPage.service.MainPageService;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainPageController {

	private final MainPageService mainPageService;

	//메인 페이지 정보 불러오기
	@GetMapping("/main")
	public ResponseDto<?> getMain() {
		return mainPageService.getMain();
	}

	//지역별 산 목록 불러오기
	@Cacheable(value = "Mountain", key = "'tagRegion'", cacheManager = "redisCacheManager")
	@GetMapping("/mountains/region/{region}")
	public ResponseDto<?> getRegionList(@PathVariable String region) {
		return mainPageService.getRegionList(region);
	}

	//태그 관련 목록 불러오기(난이도)
	@Cacheable(value = "Mountain", key = "'tagLevel'", cacheManager = "redisCacheManager")
	@GetMapping("/mountains/level/{level}")
	public ResponseDto<?> getLevelList(@PathVariable String level) {
		return mainPageService.getLevelList(level);
	}

	//태그 관련 목록 불러오기(계절)
	@Cacheable(value = "Mountain", key = "'tagSeason'", cacheManager = "redisCacheManager")
	@GetMapping("/mountains/season/{season}")
	public ResponseDto<?> getSeasonList(@PathVariable String season) {
		return mainPageService.getSeasonList(season);
	}

	//태그 관련 목록 불러오기(시간)
	@Cacheable(value = "Mountain", key = "'tagTime'", cacheManager = "redisCacheManager")
	@GetMapping("/mountains/time/{time}")
	public ResponseDto<?> getTimeList(@PathVariable String time) {
		return mainPageService.getTimeList(time);
	}

}

