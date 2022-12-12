package MTZ.mountainz.domain.mainPage.service;

import MTZ.mountainz.domain.certification.entity.Certification;
import MTZ.mountainz.domain.certification.repository.CertificationRepository;
import MTZ.mountainz.domain.detailPageTwo.dto.response.CertificationResponseDto;
import MTZ.mountainz.domain.mainPage.dto.MainPageResponseDto;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.domain.mountain.dto.response.MountainListDto;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import MTZ.mountainz.domain.mountain.repository.MountainRepository;
import MTZ.mountainz.domain.tag.dto.TagResponseDto;
import MTZ.mountainz.domain.tag.entity.Tag;
import MTZ.mountainz.domain.tag.repository.TagRepository;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final MemberRepository memberRepository;
    private final MountainRepository mountainRepository;
    private final CertificationRepository certificationRepository;
    private final TagRepository tagRepository;

    //메인 페이지 정보 불러오기
    @Transactional(readOnly = true)
    public ResponseDto<?> getMain() {

        List<Member> topList = memberRepository.findByCertificationPointGreaterThanOrderByCertificationPointDesc(0);
        if (topList.size() > 3)
            topList = topList.subList(0, 3);
        List<String> topMembers = topList.stream().map(Member::getNickName).collect(Collectors.toList());
        List<String> topMembersPhoto = topList.stream().map(Member::getProfilePhoto).collect(Collectors.toList());
        List<Integer> topMembersCertificationPoint = topList.stream().map(Member::getCertificationPoint).collect(
                Collectors.toList());

        List<Tag> imsiTagList = tagRepository.findAll();
        List<TagResponseDto> addTagList = new ArrayList<>();

        for (Tag imsiTag : imsiTagList) {
            addTagList.add(
                    TagResponseDto.builder()
                            .name(imsiTag.getName())
                            .img(imsiTag.getImg())
                            .build()
            );
        }

        List<Certification> photoList = certificationRepository.findAll();
        List<CertificationResponseDto> certificationPhotoList = new ArrayList<>();

        for (Certification imsiPhoto : photoList) {
            certificationPhotoList.add(
                    CertificationResponseDto.builder()
                            .photo(imsiPhoto.getPhoto())
                            .build()
            );
        }
        return ResponseDto.success(
                MainPageResponseDto.builder()
                        .topMembers(topMembers)
                        .topMembersPhoto(topMembersPhoto)
                        .certificationPhoto(certificationPhotoList)
                        .tagList(addTagList)
                        .topMembersCertificationPoint(topMembersCertificationPoint)
                        .build()
        );

    }

    //지역별 산 목록 불러오기
    @Transactional(readOnly = true)
    public ResponseDto<?> getRegionList(String region) {

        List<Mountain> mountainList = mountainRepository.findByRegion(region);
        List<MountainListDto> mountainRegionList = new ArrayList<>();

        for (Mountain imsiMountain : mountainList) {
            mountainRegionList.add(
                    MountainListDto.builder()
                            .img(imsiMountain.getImg())
                            .name(imsiMountain.getName())
                            .region(imsiMountain.getRegion())
                            .build()
            );
        }
        return ResponseDto.success(mountainRegionList);
    }

    //태그 관련 목록 불러오기(난이도)
    @Transactional(readOnly = true)
    public ResponseDto<?> getLevelList(String level) {

        List<Mountain> mountainList = mountainRepository.findByLevel(level);
        List<MountainListDto> mountainLevelList = new ArrayList<>();

        for (Mountain imsiLevel : mountainList) {
            mountainLevelList.add(
                    MountainListDto.builder()
                            .img(imsiLevel.getImg())
                            .name(imsiLevel.getName())
                            .level(imsiLevel.getLevel())
                            .build()
            );
        }
        return ResponseDto.success(mountainLevelList);
    }

    //태그 관련 목록 불러오기(계절)
    @Transactional(readOnly = true)
    public ResponseDto<?> getSeasonList(String season) {

        List<Mountain> mountainList = mountainRepository.findBySeason(season);
        List<MountainListDto> mountainSeasonList = new ArrayList<>();

        for (Mountain imsiSeason : mountainList) {
            mountainSeasonList.add(
                    MountainListDto.builder()
                            .img(imsiSeason.getImg())
                            .name(imsiSeason.getName())
                            .season(imsiSeason.getSeason())
                            .build()
            );
        }
        return ResponseDto.success(mountainSeasonList);
    }

    //태그 관련 목록 불러오기(시간)
    @Transactional(readOnly = true)
    public ResponseDto<?> getTimeList(String time) {

        List<Mountain> mountainList = mountainRepository.findByTime(time);
        List<MountainListDto> mountainTimeList = new ArrayList<>();

        for (Mountain imsiTime : mountainList) {
            mountainTimeList.add(
                    MountainListDto.builder()
                            .img(imsiTime.getImg())
                            .name(imsiTime.getName())
                            .time(imsiTime.getTime())
                            .build()
            );
        }
        return ResponseDto.success(mountainTimeList);
    }
}

