package MTZ.mountainz.domain.detailPageOne.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MTZ.mountainz.domain.detailPageOne.service.DetailPageOneService;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DetailPageOneController {
	private final DetailPageOneService detailPageOneService;

	// 상세페이지1 정보 불러오기
	@GetMapping("/mountainList")
	public ResponseDto<?> detailPageOneList() {
		return detailPageOneService.detailPageOneList();
	}
}
