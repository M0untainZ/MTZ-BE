package MTZ.mountainz.domain.detailPageTwo.controller;

import java.io.IOException;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import MTZ.mountainz.domain.detailPageTwo.service.DetailPageTwoService;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DetailPageTwoController {
	private final DetailPageTwoService detailPageTwoService;
	private final MemberRepository memberRepository;

	// 상세페이지2 정보 불러오기
	@GetMapping("/mountain/{mountainId}")
	@ApiOperation(value = "상세페이지2 정보 불러오기", notes = "상세페이지2 정보 불러오기 API")
	public ResponseDto<?> detailPageTwoList(@PathVariable Long mountainId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("확인용 name : " + authentication.getName());
		System.out.println("확인용 principal : " + authentication.getPrincipal().toString());
		System.out.println("확인용 details : " + authentication.getDetails());
		String email = authentication.getName();
		System.out.println("email 확인 " + email);

		return detailPageTwoService.detailPageTwoList(mountainId, email);
	}

	// 좋아요 체크(입력)
	@PostMapping("/mountain/{mountainId}/like")
	@ApiOperation(value = "좋아요 체크(입력)", notes = "좋아요 체크(입력) API")
	public ResponseDto<?> likeUp(@PathVariable Long mountainId,
		@AuthenticationPrincipal UserDetails userDetails, Long memberId) {
		return detailPageTwoService.likeUp(mountainId, userDetails.getUsername(), memberId);
	}

	// 인증하기 버튼
	@PostMapping("/mountain/{mountainId}/certification")
	@ApiOperation(value = "인증하기 버튼", notes = "인증하기 버튼 API")
	public ResponseDto<?> addCertification(@PathVariable Long mountainId,
		@RequestPart(required = false, value = "photo") List<MultipartFile> multipartFile,
		@AuthenticationPrincipal UserDetails userDetails) throws IOException {
		return detailPageTwoService.addCertification(mountainId, multipartFile, userDetails.getUsername());
	}
}
