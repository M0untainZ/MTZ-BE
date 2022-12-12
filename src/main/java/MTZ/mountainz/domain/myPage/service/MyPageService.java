package MTZ.mountainz.domain.myPage.service;

import MTZ.mountainz.domain.badge.entity.MemberBadge;
import MTZ.mountainz.domain.badge.repository.MemberBadgeRepository;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.domain.myPage.dto.MemberBadgeResponseDto;
import MTZ.mountainz.domain.myPage.dto.MyPageRequestDto;
import MTZ.mountainz.domain.myPage.dto.MyPageResponseDto;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.exception.ErrorCode;
import MTZ.mountainz.global.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;
    private final MemberBadgeRepository memberBadgeRepository;

    private Member getMember(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_NOT_FOUND_404)
        );
        return member;
    }

    //마이 페이지 정보 불러오기
    @Transactional(readOnly = true)
    public ResponseDto<?> getMyPageList(String email) {

        Member member = getMember(email);

        List<MemberBadge> memberBadgeList = memberBadgeRepository.findAllByMemberId(member.getId());
        List<MemberBadgeResponseDto> memberBadgeResponseDtoList = new ArrayList<>();
        for (MemberBadge memberBadge : memberBadgeList) {
            memberBadgeResponseDtoList.add(
                    MemberBadgeResponseDto.builder()
                            .id(memberBadge.getBadge().getId())
                            .badgeName(memberBadge.getBadge().getBadgeName())
                            .content(memberBadge.getBadge().getContent())
                            .img(memberBadge.getBadge().getImg())
                            .openTime(memberBadge.getOpenTime())
                            .build()
            );
        }

        return ResponseDto.success(
                MyPageResponseDto.builder()
                        .profilePhoto(member.getProfilePhoto())
                        .nickName(member.getNickName())
                        .region(member.getRegion())
                        .badgeList(memberBadgeResponseDtoList)
                        .badgeName(member.getBadgeName())
                        .build()
        );
    }

    //마이 페이지 수정
    @Transactional
    public ResponseDto<?> updateMyPage(MyPageRequestDto myPageRequestDto, String email) {
        Member member = getMember(email);

        member.update(myPageRequestDto);
        return ResponseDto.success("수정이 완료되었습니다.");
    }
}
