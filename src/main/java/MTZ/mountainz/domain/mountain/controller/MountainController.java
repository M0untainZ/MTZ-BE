package MTZ.mountainz.domain.mountain.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import MTZ.mountainz.domain.mountain.dto.request.MountainRequestDto;
import MTZ.mountainz.domain.mountain.service.MountainService;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MountainController {
	private final MountainService mountainService;

	// 산 등록
	@PostMapping("/add")
	public ResponseDto<?> createMountain(
		@RequestPart(required = false, value = "file") List<MultipartFile> multipartFile,
		@RequestPart(value = "mountainRequsetDto") MountainRequestDto mountainRequestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) throws IOException {
		return mountainService.createMountain(multipartFile, mountainRequestDto,
			userDetailsImpl.getMember().getEmail());
	}
}
