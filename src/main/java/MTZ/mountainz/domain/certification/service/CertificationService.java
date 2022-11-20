package MTZ.mountainz.domain.certification.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.s3.AmazonS3Client;

import MTZ.mountainz.domain.certification.dto.request.CertificationRequestDto;
import MTZ.mountainz.domain.certification.dto.request.PhotoFilterRequestDto;
import MTZ.mountainz.domain.certification.dto.response.CertificationFilterResponseDto;
import MTZ.mountainz.domain.certification.dto.response.CertificationPhotoListResponseDto;
import MTZ.mountainz.domain.certification.entity.Certification;
import MTZ.mountainz.domain.certification.repository.CertificationRepository;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.exception.ErrorCode;
import MTZ.mountainz.global.exception.RequestException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificationService {
	private final CertificationRepository certificationRepository;
	private final MemberRepository memberRepository;

	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	// email 이용해서 Member 객체 만들기 및 멤버정보 확인
	private Member getMember(String email) {
		Member member = memberRepository.findByEmail(email).orElseThrow(
			() -> new RequestException(ErrorCode.MEMBER_NOT_FOUND_404)
		);
		return member;
	}

	// 인증 페이지에 인증사진 불러오기
	public ResponseDto<?> certificationList(String email) {
		Member member = getMember(email);

		// cetification 테이블 정보 다 불러오기
		List<Certification> certificationList = certificationRepository.findAll();

		List<CertificationPhotoListResponseDto> certificationPhotoListResponseDtoList = new ArrayList<>();

		for (Certification certification : certificationList) {
			certificationPhotoListResponseDtoList.add(
				CertificationPhotoListResponseDto.builder()
					.photo(certification.getPhoto())
					.nickName(certification.getMember().getNickName())
					.name(certification.getMountain().getName())
					.region(certification.getMountain().getRegion())
					.build()
			);
		}

		return ResponseDto.success(certificationPhotoListResponseDtoList);
	}

	// 인증사진 삭제하기
	@Transactional
	public ResponseDto<?> certificationSakje(CertificationRequestDto certificationRequestDto) {
		String[] split = certificationRequestDto.getPhoto().split("/");
		String key = split[split.length - 1].trim();

		Certification certification = certificationRepository.findById(certificationRequestDto.getCertificationId())
			.orElseThrow(
				() -> new RequestException(ErrorCode.CERTIFICATION_NOT_FOUND_404)
			);

		// member에 certificationPoint 3 감소시키기
		certification.getMember().deleteCertificationPoint(3);

		// S3에서 삭제
		amazonS3Client.deleteObject(bucketName, key);

		// 인증사진 삭제
		certificationRepository.deleteById(certificationRequestDto.getCertificationId());

		return ResponseDto.success(key + "를 삭제 완료 했습니다");
	}

	//인증사진 필터 검색
	public ResponseDto<?> getPhotoSearch(PhotoFilterRequestDto photoFilterRequestDto) {

		List<CertificationFilterResponseDto> photosList = certificationRepository.findByPhotosFilter(
			photoFilterRequestDto);
		return ResponseDto.success(photosList);
	}
}
