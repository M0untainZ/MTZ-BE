package MTZ.mountainz.domain.detailPageOne.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MTZ.mountainz.domain.detailPageOne.dto.request.FilterRequestDto;
import MTZ.mountainz.domain.detailPageOne.dto.request.KeywordRequestDto;
import MTZ.mountainz.domain.detailPageOne.service.DetailPageOneService;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DetailPageOneController {
	private final DetailPageOneService detailPageOneService;

	// 상세페이지1 정보 불러오기
	@GetMapping("/mountains")
	public ResponseDto<?> detailPageOneList() {
		return detailPageOneService.detailPageOneList();
	}

	// 퀴즈풀기
	// @PostMapping("/mountainList/{mountainId}/quiz")
	// public ResponseDto<?> solveQuiz(@RequestBody QuizRequestDto quizRequestDto,
	// 	@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
	// 	return DetailPageOneService.solveQuiz(quizRequestDto, userDetailsImpl.getMember().getEmail());
	// }

	// 키워드 검색 (산이름만 추후 queryDSL로 전체 조회할 예정)
	@PostMapping("/mountains/search")
	public ResponseDto<?> getKeywordSearch(@RequestBody KeywordRequestDto keywordRequestDto) {
		return detailPageOneService.getKeywordSearch(keywordRequestDto);
	}

	// 필터 검색
	@PostMapping("/mountains/filter")
	public ResponseDto<?> getFilterSearch(@RequestBody FilterRequestDto filterRequestDto) {
		return detailPageOneService.getFilterSearch(filterRequestDto);
	}
}
