package MTZ.mountainz.domain.member.controller;

import MTZ.mountainz.domain.member.dto.request.KakaoUserInfoDto;
import MTZ.mountainz.domain.member.service.KakaoUserService;
import MTZ.mountainz.global.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class SocialController {
    private final KakaoUserService kakaoUserService;

    // 카카오 로그인
    @GetMapping("/kakao/callback")
    public ResponseDto<KakaoUserInfoDto> kakaoLogin(@RequestParam(name = "code") String code,
                                                    HttpServletResponse response) throws
            JsonProcessingException {
        return kakaoUserService.kakaoLogin(code, response);
    }
}
