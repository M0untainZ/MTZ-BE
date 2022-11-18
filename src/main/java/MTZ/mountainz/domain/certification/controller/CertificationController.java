package MTZ.mountainz.domain.certification.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MTZ.mountainz.domain.certification.dto.request.CertificationRequestDto;
import MTZ.mountainz.domain.certification.dto.request.PhotoFilterRequestDto;
import MTZ.mountainz.domain.certification.service.CertificationService;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CertificationController {
	private final CertificationService certificationService;

	// 인증 페이지에 인증사진 불러오기
	@GetMapping("/photos")
	public ResponseDto<?> certificationList(@AuthenticationPrincipal UserDetails userDetails) {
		return certificationService.certificationList(userDetails.getUsername());
	}

	// 인증사진 삭제하기
	@DeleteMapping("/photos/sakje")
	public ResponseDto<?> certificationSakje(@RequestBody CertificationRequestDto certificationRequestDto) {
		return certificationService.certificationSakje(certificationRequestDto);
	}

	//인증사진 필터 검색
	@GetMapping("/photos/filter")
	public ResponseDto<?> getPhotoSearch(@RequestBody PhotoFilterRequestDto photoFilterRequestDto) {
		return certificationService.getPhotoSearch(photoFilterRequestDto);
	}
}
