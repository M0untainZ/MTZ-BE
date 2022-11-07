package MTZ.mountainz.domain.member.controller;


import MTZ.mountainz.domain.member.dto.request.MemberRequest;
import MTZ.mountainz.domain.member.service.MemberService;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody @Valid MemberRequest memberRequest) {
        return ResponseDto.success(memberService.signup(memberRequest));
    }




}
