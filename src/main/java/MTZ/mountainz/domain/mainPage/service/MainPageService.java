package MTZ.mountainz.domain.mainPage.service;


import MTZ.mountainz.domain.certification.entity.Certification;
import MTZ.mountainz.domain.certification.repository.CertificationRepositoy;
import MTZ.mountainz.domain.detailPageTwo.dto.response.CertificationResponseDto;
import MTZ.mountainz.domain.mainPage.dto.MainPageResponseDto;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.domain.mountain.dto.response.MountainListDto;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import MTZ.mountainz.domain.mountain.repository.MountainRepository;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.exception.ErrorCode;
import MTZ.mountainz.global.exception.RequestException;
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
    private final CertificationRepositoy certificationRepositoy;

    //메인 페이지 정보 불러오기
    @Transactional(readOnly = true)
    public ResponseDto<?> getMain() {

        //TOP 3명만 불러오기
//        Member member = memberRepository.findById(id).orElseThrow(()-> new RequestException(ErrorCode.MEMBER_NOT_FOUND_404));
//        List<Member> topList = memberRepository.findByCertifiCationPointGreaterThanOrderByCertifiCationPointDesc(0);
//        if(topList.size()>3) topList =topList.subList(0,3);
//        List<String> topMembers =topList.stream().map(Member::getNickName).collect(Collectors.toList());

        //인증 사진 리스트
        List<Certification> photoList = certificationRepositoy.findAll();
        List<CertificationResponseDto> certificationPhotoList = new ArrayList<>();

        for(Certification imsiPhoto : photoList) {
            certificationPhotoList.add(
                    CertificationResponseDto.builder()
                            .photo(imsiPhoto.getPhoto())
                            .build()
            );
        }
        return ResponseDto.success(
                MainPageResponseDto.builder()
//                        .topMembers(topMembers)
                        .certificationPhoto(certificationPhotoList)
                        .build()
        );

    }
    //지역별 산 목록 불러오기
    @Transactional(readOnly = true)
    public ResponseDto<?> getRegionList(String mountainRegion) {
        // 해당 산의 지역 찾기
        List<Mountain> mountainList = mountainRepository.findByMountainRegion(mountainRegion);
        List<MountainListDto> mountainRegionList = new ArrayList<>();

        for (Mountain imsiMountain : mountainList) {
            mountainRegionList.add(
                    MountainListDto.builder()
                            .mountainImg(imsiMountain.getMountainImg())
                            .mountainName(imsiMountain.getMountainName())
                            .mountainRegion(imsiMountain.getMountainRegion())
                            .build()
            );
        }
            return ResponseDto.success(mountainRegionList);
    }
        //태그 관련 목록 불러오기(난이도)
        @Transactional(readOnly = true)
        public ResponseDto<?> getLevelList(String mountainLevel) {
            // 해당 산의 난이도 찾기
            List<Mountain> mountainList =mountainRepository.findByMountainLevel(mountainLevel); //산 리스트에서 레벨 목록 뽑기
            List<MountainListDto> mountainLevelList = new ArrayList<>();

            for (Mountain imsiLevel : mountainList) {
                mountainLevelList.add(
                        MountainListDto.builder()
                                .mountainImg(imsiLevel.getMountainImg())
                                .mountainName(imsiLevel.getMountainName())
                                .mountainLevel(imsiLevel.getMountainLevel())
                                .build()
                );
            }
            return ResponseDto.success(mountainLevelList);
        }
        //태그 관련 목록 불러오기(계절)
        @Transactional(readOnly = true)
        public ResponseDto<?> getSeasonList(String mountainSeason) {
            // 해당 산의 계절 찾기
            List<Mountain> mountainList =mountainRepository.findByMountainSeason(mountainSeason); //산 리스트에서 계절 목록 뽑기
            List<MountainListDto> mountainSeasonList = new ArrayList<>();

            for (Mountain imsiSeason : mountainList) {
                mountainSeasonList.add(
                        MountainListDto.builder()
                                .mountainImg(imsiSeason.getMountainImg())
                                .mountainName(imsiSeason.getMountainName())
                                .mountainSeason(imsiSeason.getMountainSeason())
                                .build()
                );
            }
            return ResponseDto.success(mountainSeasonList);
        }
}

