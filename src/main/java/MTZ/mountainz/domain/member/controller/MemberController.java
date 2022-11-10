package MTZ.mountainz.domain.member.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MTZ.mountainz.domain.member.dto.request.EmailConfirmDto;
import MTZ.mountainz.domain.member.dto.request.LoginRequestDto;
import MTZ.mountainz.domain.member.dto.request.MemberRequestDto;
import MTZ.mountainz.domain.member.dto.request.NickNameConfirmDto;
import MTZ.mountainz.domain.member.dto.response.LoginResponseDto;
import MTZ.mountainz.domain.member.service.MemberService;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;

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
	public ResponseDto<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto,
		HttpServletResponse response) {
		return memberService.login(loginRequestDto, response);
	}

	// 이메일 중복 확인
	@PostMapping("/emailConfirm")
	public ResponseDto<String> emailConfirm(@RequestBody EmailConfirmDto emailConfirmDto) {
		return memberService.emailConfirm(emailConfirmDto.getEmail());
	}

	// 닉네임 중복 확인
	@PostMapping("/nickNameConfirm")
	public ResponseDto<String> nickNameConfirm(@RequestBody NickNameConfirmDto nickNameConfirmDto) {
		return memberService.nickNameConfirm(nickNameConfirmDto.getNickName());
	}
}
