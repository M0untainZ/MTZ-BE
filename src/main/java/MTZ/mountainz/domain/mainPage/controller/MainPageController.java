package MTZ.mountainz.domain.mainPage.controller;

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
	@GetMapping("/mountains/{region}")
	public ResponseDto<?> getRegionList(@PathVariable String region) {
		return mainPageService.getRegionList(region);
	}

	//태그 관련 목록 불러오기(난이도)
	@GetMapping("/mountains/{level}")
	public ResponseDto<?> getLevelList(@PathVariable String level) {
		return mainPageService.getLevelList(level);
	}

	//태그 관련 목록 불러오기(계절)
	@GetMapping("/mountains/{season}")
	public ResponseDto<?> getSeasonList(@PathVariable String season) {
		return mainPageService.getSeasonList(season);
	}
}

