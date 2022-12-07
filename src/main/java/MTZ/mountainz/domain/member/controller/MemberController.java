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
import MTZ.mountainz.domain.member.dto.response.SignupResponseDto;
import MTZ.mountainz.domain.member.service.MemberService;
import MTZ.mountainz.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = {"mountainz API"})
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입", notes = "회원가입 API")
	@ApiImplicitParam(name = "userRequestDto", value = "회원가입시 입력된 정보")
	public ResponseDto<SignupResponseDto> signup(@RequestBody @Valid MemberRequestDto memberRequestDto) {
		return memberService.signup(memberRequestDto);
	}

	// 로그인
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "로그인 API")
	public ResponseDto<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto,
		HttpServletResponse response) {
		return memberService.login(loginRequestDto, response);
	}

	// 이메일 중복 확인
	@PostMapping("/emailConfirm")
	@ApiOperation(value = "이메일 중복 확인", notes = "이메일 중복 확인 API")
	public ResponseDto<String> emailConfirm(@RequestBody EmailConfirmDto emailConfirmDto) {
		return memberService.emailConfirm(emailConfirmDto.getEmail());
	}

	// 닉네임 중복 확인
	@PostMapping("/nickNameConfirm")
	@ApiOperation(value = "닉네임 중복 확인", notes = "닉네임 중복 확인 API")
	public ResponseDto<String> nickNameConfirm(@RequestBody NickNameConfirmDto nickNameConfirmDto) {
		return memberService.nickNameConfirm(nickNameConfirmDto.getNickName());
	}
}
