package MTZ.mountainz.domain.myPage.controller;

import MTZ.mountainz.domain.myPage.dto.MyPageRequestDto;
import MTZ.mountainz.domain.myPage.service.MyPageService;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {

    private final MyPageService myPageService;

    //마이 페이지 정보 불러오기
    @GetMapping("/myPageList")
    public ResponseDto<?> getMyPageList(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return myPageService.getMyPageList(userDetailsImpl.getMember().getEmail());
    }

    //마이 페이지 수정
    @PutMapping("/myPageSujung")
    public ResponseDto<?> updateMyPage(@RequestBody MyPageRequestDto myPageRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return myPageService.updateMyPage(myPageRequestDto, userDetailsImpl.getMember().getEmail());
    }
}