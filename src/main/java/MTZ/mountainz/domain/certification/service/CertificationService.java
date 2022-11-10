package MTZ.mountainz.domain.certification.service;

import MTZ.mountainz.domain.certification.dto.request.CertificationRequestDto;
import MTZ.mountainz.domain.certification.dto.response.CertificationPhotoListResponseDto;
import MTZ.mountainz.domain.certification.entity.Certification;
import MTZ.mountainz.domain.certification.repository.CertificationRepositoy;
import MTZ.mountainz.domain.detailPageOne.dto.response.DetailPageOneResponseDto;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import MTZ.mountainz.global.dto.ResponseDto;
import com.amazonaws.services.s3.AmazonS3Client;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationService {
    private final CertificationRepositoy certificationRepositoy;

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    // 인증 페이지에 인증사진 불러오기
    public ResponseDto<?> certificationList() {
        // cetification 테이블 정보 다 불러오기
        List<Certification> certificationList = certificationRepositoy.findAll();

        List<CertificationPhotoListResponseDto> certificationPhotoListResponseDtoList = new ArrayList<>();

        for(Certification certification  : certificationList) {
            certificationPhotoListResponseDtoList.add(
                    CertificationPhotoListResponseDto.builder()
                            .photo(certification.getPhoto())
                            .nickName(certification.getMember().getNickName())
                            .build()
            );
        }

        return ResponseDto.success(certificationPhotoListResponseDtoList);
    }

    // 인증사진 삭제하기
    public ResponseDto<?> certificationSakje(CertificationRequestDto certificationRequestDto) {
        String[] split = certificationRequestDto.getPhoto().split("/");
        String key = split[split.length-1].trim();

        // S3에서 삭제
        amazonS3Client.deleteObject(bucketName, key);

        // 인증사진 삭제
        certificationRepositoy.deleteById(certificationRequestDto.getCertificationId());

        return ResponseDto.success(key + "를 삭제 완료 했습니다");
    }
}
