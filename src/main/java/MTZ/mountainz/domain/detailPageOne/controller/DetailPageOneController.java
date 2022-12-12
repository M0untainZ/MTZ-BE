package MTZ.mountainz.domain.detailPageOne.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	@ApiOperation(value = "상세페이지1 정보 불러오기", notes = "상세페이지1 정보 불러오기 API")
	public ResponseDto<?> detailPageOneList(@PageableDefault(size = 12) Pageable pageable) {
		return detailPageOneService.detailPageOneList(pageable);
	}

	// 키워드 검색
	@PostMapping("/mountains/search")
	@ApiOperation(value = "키워드 검색", notes = "키워드 검색 API")
	public ResponseDto<?> getKeywordSearch(@RequestBody KeywordRequestDto keywordRequestDto) {
		return detailPageOneService.getKeywordSearch(keywordRequestDto);
	}

	// 필터 검색
	@PostMapping("/mountains/filter")
	@ApiOperation(value = "필터 검색", notes = "필터 검색 API")
	public ResponseDto<?> getFilterSearch(@RequestBody FilterRequestDto filterRequestDto) {
		return detailPageOneService.getFilterSearch(filterRequestDto);
	}
}
