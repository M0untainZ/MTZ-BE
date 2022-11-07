package MTZ.mountainz.domain.member.service;


import MTZ.mountainz.domain.member.dto.request.MemberRequest;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.domain.member.repository.RefreshTokenRepository;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.exception.ErrorCode;
import MTZ.mountainz.global.exception.RequestException;
import MTZ.mountainz.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ResponseDto<String> signup(MemberRequest memberRequest) {

        if(memberRepository.findByEmail(memberRequest.getEmail()).isPresent()){
            throw new RequestException(ErrorCode.EMAIL_DUPLICATION_409);
        }
        memberRequest.setEncodedPwd(passwordEncoder.encode(memberRequest.getPassword()));
        Member member = new Member(memberRequest);

        memberRepository.save(member);
        return ResponseDto.success("회원가입 완료");
    }


}
