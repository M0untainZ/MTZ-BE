package MTZ.mountainz.domain.myPage.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MTZ.mountainz.domain.myPage.dto.MyPageRequestDto;
import MTZ.mountainz.domain.myPage.service.MyPageService;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {

	private final MyPageService myPageService;

	//마이 페이지 정보 불러오기
	@GetMapping("/myPages")
	public ResponseDto<?> getMyPageList(@AuthenticationPrincipal UserDetails userDetails) {
		return myPageService.getMyPageList(userDetails.getUsername());
	}

	//마이 페이지 수정
	@PutMapping("/myPageSujung")
	public ResponseDto<?> updateMyPage(@RequestBody MyPageRequestDto myPageRequestDto,
		@AuthenticationPrincipal UserDetails userDetails) {
		return myPageService.updateMyPage(myPageRequestDto, userDetails.getUsername());
	}
}