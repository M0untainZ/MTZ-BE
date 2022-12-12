package MTZ.mountainz.domain.detailPageTwo.service;

import MTZ.mountainz.domain.badge.entity.Badge;
import MTZ.mountainz.domain.badge.entity.MemberBadge;
import MTZ.mountainz.domain.badge.repository.BadgeRepository;
import MTZ.mountainz.domain.badge.repository.MemberBadgeRepository;
import MTZ.mountainz.domain.certification.entity.Certification;
import MTZ.mountainz.domain.certification.repository.CertificationRepository;
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
import org.springframework.http.HttpStatus;
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
    private final CertificationRepository certificationRepository;
    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final BadgeRepository badgeRepository;
    private final MemberBadgeRepository memberBadgeRepository;

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
    public ResponseDto<?> detailPageTwoList(Long mountainId, String email) {
        Mountain mountain = mountainRepository.findById(mountainId).orElseThrow(
                () -> new RequestException(ErrorCode.MOUNTAIN_NOT_FOUND_404)
        );

        Optional<Likes> imsiLike = likesRepository.findByMountainIdAndMemberEmail(mountainId, email);

        boolean correctLike;
        if (imsiLike.isPresent()) {
            correctLike = true;
        } else {
            correctLike = false;
        }

        Long countLike = likesRepository.countAllByMountainId(mountainId);

        List<Certification> certificationList = certificationRepository.findAllByMountainId(mountainId);
        List<CertificationResponseDto> certificationResponseDtoList = new ArrayList<>();

        for (Certification certification : certificationList) {
            certificationResponseDtoList.add(
                    CertificationResponseDto.builder()
                            .photo(certification.getPhoto())
                            .build()
            );
        }

        return ResponseDto.success(
                DetailPageTwoResponseDto.builder()
                        .name(mountain.getName())
                        .img(mountain.getImg())
                        .region(mountain.getRegion())
                        .level(mountain.getLevel())
                        .season(mountain.getSeason())
                        .time(mountain.getTime())
                        .certificatedMountainList(certificationResponseDtoList)
                        .juso(mountain.getJuso())
                        .correctLike(correctLike)
                        .countLike(countLike)
                        .build()
        );
    }

    // 좋아요 체크(입력)
    @Transactional
    public ResponseDto<?> likeUp(Long mountainId, String email) {

        Optional<Likes> imsiLike = likesRepository.findByMountainIdAndMemberEmail(mountainId, email);
        Member member = getMember(email);
        Mountain mountain = new Mountain(mountainId);


        boolean correctLike;
        if (imsiLike.isPresent()) {

            correctLike = false;
            likesRepository.delete(imsiLike.get());
        } else {

            correctLike = true;
            Likes likes = new Likes(mountain, member);
            likesRepository.save(likes);
        }

        Long memberLike1 = likesRepository.countAllByMemberId(member.getId());

        Optional<MemberBadge> imsiBadgeLike5 = memberBadgeRepository.findByBadgeIdAndMemberId(5L, member.getId());
        Optional<MemberBadge> imsiBadgeLike6 = memberBadgeRepository.findByBadgeIdAndMemberId(6L, member.getId());

        Badge badge = null;
        boolean correctBadge = false;

        if (memberLike1.equals(5L)) {
            if (imsiBadgeLike5.isPresent()) {

            } else {
                badge = badgeRepository.findById(5L).orElseThrow(
                        () -> new IllegalArgumentException()
                );
                memberBadgeRepository.save(new MemberBadge(badge, member));
                correctBadge = true;

            }
        }

        Long memberLike2 = likesRepository.countAllByMemberId(member.getId());
        if (memberLike2.equals(10L)) {
            if (imsiBadgeLike6.isPresent()) {

            } else {
                badge = badgeRepository.findById(6L).orElseThrow(
                        () -> new IllegalArgumentException()
                );
                memberBadgeRepository.save(new MemberBadge(badge, member));
                correctBadge = true;

            }
        }


        Long countLike = likesRepository.countAllByMountainId(mountainId);

        return ResponseDto.success(
                LikesResponseDto.builder()
                        .correctLike(correctLike)
                        .countLike(countLike)
                        .badge(badge)
                        .correctBadge(correctBadge)
                        .build()
        );
    }

    // 인증하기 버튼
    @Transactional
    public ResponseDto<?> addCertification(Long mountainId, List<MultipartFile> multipartFile, String email) throws
            IOException {
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

                certificationRepository.save(certification);
            } else {

                return ResponseDto.fail(HttpStatus.NOT_FOUND, "사진을 넣어주세요");
            }
        }

        member.updateCertificationPoint(3);

        Optional<Likes> imsiLike = likesRepository.findByMountainIdAndMemberEmail(mountainId, email);

        boolean correctLike;
        if (imsiLike.isPresent()) {
            correctLike = true;
        } else {
            correctLike = false;
        }

        Long countLike = likesRepository.countAllByMountainId(mountainId);

        List<Certification> certificationList = certificationRepository.findAllByMountainId(mountainId);
        List<CertificationResponseDto> certificationResponseDtoList = new ArrayList<>();

        for (Certification certification : certificationList) {
            certificationResponseDtoList.add(
                    CertificationResponseDto.builder()
                            .photo(certification.getPhoto())
                            .build()
            );
        }

        Optional<MemberBadge> CertificationBadge1 = memberBadgeRepository.findByBadgeIdAndMemberId(2L, member.getId());
        Optional<MemberBadge> CertificationBadge2 = memberBadgeRepository.findByBadgeIdAndMemberId(3L, member.getId());
        Optional<MemberBadge> CertificationBadge3 = memberBadgeRepository.findByBadgeIdAndMemberId(4L, member.getId());
        Badge badge = null;
        boolean correctBadge = false;

        int imsiCertificationPoint = member.getCertificationPoint();
        if (imsiCertificationPoint == 9) {
            if (CertificationBadge1.isPresent()) {

            } else {
                badge = badgeRepository.findById(2L).orElseThrow(
                        () -> new IllegalArgumentException()
                );
                memberBadgeRepository.save(new MemberBadge(badge, member));
                correctBadge = true;

            }
        }

        if (imsiCertificationPoint == 18) {
            if (CertificationBadge2.isPresent()) {

            } else {
                badge = badgeRepository.findById(3L).orElseThrow(
                        () -> new IllegalArgumentException()
                );
                memberBadgeRepository.save(new MemberBadge(badge, member));
                correctBadge = true;

            }
        }

        if (imsiCertificationPoint == 27) {
            if (CertificationBadge3.isPresent()) {

            } else {
                badge = badgeRepository.findById(4L).orElseThrow(
                        () -> new IllegalArgumentException()
                );
                memberBadgeRepository.save(new MemberBadge(badge, member));
                correctBadge = true;
            }
        }

        return ResponseDto.success(
                DetailPageTwoResponseDto.builder()
                        .name(mountain.getName())
                        .img(mountain.getImg())
                        .region(mountain.getRegion())
                        .level(mountain.getLevel())
                        .season(mountain.getSeason())
                        .time(mountain.getTime())
                        .certificatedMountainList(certificationResponseDtoList)
                        .juso(mountain.getJuso())
                        .correctLike(correctLike)
                        .correctBadge(correctBadge)
                        .countLike(countLike)
                        .badge(badge)
                        .build()
        );
    }
}