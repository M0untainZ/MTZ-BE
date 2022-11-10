package MTZ.mountainz.domain.detailPageTwo.service;

import MTZ.mountainz.domain.certification.entity.Certification;
import MTZ.mountainz.domain.certification.repository.CertificationRepositoy;
import MTZ.mountainz.domain.detailPageTwo.dto.response.CertificationResponseDto;
import MTZ.mountainz.domain.detailPageTwo.dto.response.DetailPageTwoResponseDto;
import MTZ.mountainz.domain.like.entity.Likes;
import MTZ.mountainz.domain.like.likeResponseDto.LikesResponseDto;
import MTZ.mountainz.domain.like.repository.LikesRepository;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import MTZ.mountainz.domain.mountain.repository.MountainRepository;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.exception.ErrorCode;
import MTZ.mountainz.global.exception.RequestException;
import MTZ.mountainz.global.s3.CommonUtils;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailPageTwoService {
    private final MountainRepository mountainRepository;
    private final CertificationRepositoy certificationRepositoy;
    private final LikesRepository likesRepository;
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

    // 상세페이지2 정보 불러오기
    public ResponseDto<?> detailPageTwoList(Long mountainId) {
        Mountain mountain = mountainRepository.findById(mountainId).orElseThrow(
                () -> new RequestException(ErrorCode.MOUNTAIN_NOT_FOUND_404)
        );

        // 해당 산의 정보 불러오기
        // 해당 산의 인증이미지들 불러와서 객체로 담기
        List<Certification> certificationList = certificationRepositoy.findAllByMountainId(mountainId);
        // certification 객체를 담는 리스트 생성
        List<CertificationResponseDto> certificationResponseDtoList = new ArrayList<>();
        // 이미지 url(photo) 만 뽑은 responseDto 형태로 리스트 담기
        for(Certification certification : certificationList) {
            certificationResponseDtoList.add(
                    CertificationResponseDto.builder()
                            .photo(certification.getPhoto())
                            .build()
            );
        }

        return ResponseDto.success(
                DetailPageTwoResponseDto.builder()
                        .mountainName(mountain.getMountainName())
                        .mountainRegion(mountain.getMountainRegion())
                        .mountainLevel(mountain.getMountainLevel())
                        .mountainSeason(mountain.getMountainSeason())
                        .mountainTime(mountain.getMountainTime())
                        .mountainQuiz(mountain.getMountainQuiz())
                        .certificatedMountainList(certificationResponseDtoList)
                        .build()
        );
    }

    // 좋아요 체크(입력)
    @Transactional
    public ResponseDto<?> likeUp(Long mountainId, String email) {
        // 들어온 mountainId와 email 로 좋아요 여부 판단
        Optional<Likes> imsiLike = likesRepository.findByMountainIdAndMemberEmail(mountainId, email);
        Member member = getMember(email);
        Mountain mountain = new Mountain(mountainId);

        // 해당 산의 좋아요 true/false
        boolean correctLike;
        if(imsiLike.isPresent()) {
            // like 가 존재하면 삭제
            likesRepository.delete(imsiLike.get());
            correctLike = false;
        } else {
            // like가 없으면 등록
            Likes likes = new Likes(mountain, member);
            likesRepository.save(likes);
            correctLike = true;
        }
        
        // 해당 산의 총 좋아요 갯수
        Long countLike = likesRepository.countAllByMountainId(mountainId);

        return ResponseDto.success(
                LikesResponseDto.builder()
                        .countLike(countLike)
                        .correctLike(correctLike)
                        .build()
        );
    }

    // 위도, 경도에 따라 범위에 해당하면 -> hide된 버튼 활성화

    // 인증하기 버튼
    @Transactional
    public ResponseDto<?> addCertification(Long mountainId, List<MultipartFile> multipartFile, String email) throws IOException {
        Member member = getMember(email);
        Mountain mountain = mountainRepository.findById(mountainId).orElseThrow(
                () -> new RequestException(ErrorCode.MOUNTAIN_NOT_FOUND_404)
        );
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

                Certification certification = new Certification(imgUrl, mountain, member);

                certificationRepositoy.save(certification);
            }
        }

        // member에 certificationPoint 3 증가시키기
        member.updateCertificationPoint(3);

        return ResponseDto.success("인증이 완료되었습니다");
    }
}
