package MTZ.mountainz.domain.certification.controller;

import MTZ.mountainz.domain.certification.dto.request.CertificationRequestDto;
import MTZ.mountainz.domain.certification.entity.Certification;
import MTZ.mountainz.domain.certification.service.CertificationService;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CertificationController {
    private final CertificationService certificationService;

    // 인증 페이지에 인증사진 불러오기
    @GetMapping("/photoList")
    public ResponseDto<?> certificationList (){
        return certificationService.certificationList();
    }

    // 인증사진 삭제하기
    @DeleteMapping("/photoList/sakje")
    public ResponseDto<?> certificationSakje (@RequestBody CertificationRequestDto certificationRequestDto) {
        return certificationService.certificationSakje(certificationRequestDto);
    }

}
