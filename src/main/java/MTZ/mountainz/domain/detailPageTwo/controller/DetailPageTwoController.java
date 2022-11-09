package MTZ.mountainz.domain.detailPageTwo.controller;

import MTZ.mountainz.domain.detailPageTwo.service.DetailPageTwoService;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import MTZ.mountainz.domain.mountain.service.MountainService;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DetailPageTwoController {
    private final DetailPageTwoService detailPageTwoService;

    // 상세페이지2 정보 불러오기
    @GetMapping("/mountain/{mountainId}")
    public ResponseDto<?> detailPageTwoList(@PathVariable Long mountainId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return detailPageTwoService.detailPageTwoList(mountainId, userDetailsImpl.getMember().getEmail());
    }

    // 좋아요 체크(입력)
    @PostMapping("/mountain/{mountainId}/like")
    public ResponseDto<?> likeUp(@PathVariable Long mountainId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return detailPageTwoService.likeUp(mountainId, userDetailsImpl.getMember().getEmail());
    }

    // 위도, 경도에 따라 범위에 해당하면 -> hide된 버튼 활성화

    // 인증하기 버튼
    @PostMapping("/mountain/{mountainId}/certification")
    public ResponseDto<?> addCertification(@PathVariable Long mountainId,
                                           @RequestPart(required = false, value = "photo") List<MultipartFile> multipartFile,
                                           @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) throws IOException {
        return detailPageTwoService.addCertification(mountainId, multipartFile, userDetailsImpl.getMember().getEmail());
    }
}