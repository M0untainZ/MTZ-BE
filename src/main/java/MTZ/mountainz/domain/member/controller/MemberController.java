package MTZ.mountainz.domain.member.controller;


import MTZ.mountainz.domain.member.dto.request.LoginRequestDto;
import MTZ.mountainz.domain.member.dto.request.MemberRequestDto;
import MTZ.mountainz.domain.member.service.MemberService;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseDto<String> signup(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.signup(memberRequestDto);
    }
    // 로그인
    @PostMapping("/login")
    public ResponseDto<String> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return memberService.login(loginRequestDto, response);
    }
}
