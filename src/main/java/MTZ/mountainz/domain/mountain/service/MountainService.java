package MTZ.mountainz.domain.mountain.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;

import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.domain.mountain.dto.request.MountainRequestDto;
import MTZ.mountainz.domain.mountain.entity.Img;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import MTZ.mountainz.domain.mountain.repository.ImgRepository;
import MTZ.mountainz.domain.mountain.repository.MountainRepository;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.exception.ErrorCode;
import MTZ.mountainz.global.exception.RequestException;
import MTZ.mountainz.global.s3.CommonUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MountainService {
	private final MemberRepository memberRepository;
	private final MountainRepository mountainRepository;
	private final ImgRepository imgRepository;

	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	// email을 이용해서 Member 객체 만들기 및 멤버정보 확인
	private Member getMember(String email) {
		Member member = memberRepository.findByEmail(email).orElseThrow(
			() -> new RequestException(ErrorCode.MEMBER_NOT_FOUND_404)
		);
		return member;
	}

	// 산 등록
	public ResponseDto<?> createMountain(List<MultipartFile> multipartFile, MountainRequestDto mountainRequestDto,
		String email) throws IOException {
		Member member = getMember(email);
		Mountain mountain = new Mountain(mountainRequestDto, member);
		mountainRepository.save(mountain);

		String imgUrl = "";

		for (MultipartFile file : multipartFile) {
			if (!file.isEmpty()) {
				String fileName = CommonUtils.buildFileName(file.getOriginalFilename());
				ObjectMetadata objectMetadata = new ObjectMetadata();
				objectMetadata.setContentType(file.getContentType());

				byte[] bytes = IOUtils.toByteArray(file.getInputStream());
				objectMetadata.setContentLength(bytes.length);
				ByteArrayInputStream byteArrayIs = new ByteArrayInputStream(bytes);

				amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, byteArrayIs, objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead));
				imgUrl = amazonS3Client.getUrl(bucketName, fileName).toString();

				Img img = new Img(imgUrl, mountain);

				imgRepository.save(img);
			}
		}

		return ResponseDto.success("산 등록 성공");
	}
}
