package MTZ.mountainz.domain.myPage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import MTZ.mountainz.domain.badge.repository.BadgeRepository;
import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.domain.member.service.MemberService;
import MTZ.mountainz.domain.myPage.dto.MyPageRequestDto;
import MTZ.mountainz.domain.myPage.dto.MyPageResponseDto;
import MTZ.mountainz.global.dto.ResponseDto;
import MTZ.mountainz.global.exception.ErrorCode;
import MTZ.mountainz.global.exception.RequestException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {

	private final MemberRepository memberRepository;
	private final BadgeRepository badgeRepository;
	private final MemberService memberService;

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

		return ResponseDto.success(
			MyPageResponseDto.builder()
				.profilePhoto(member.getProfilePhoto())
				.nickName(member.getNickName())
				.region(member.getRegion())
				.badgeList(badgeRepository.findAllByMemberId(member.getId()))
				.build()
		);
	}

	//마이 페이지 수정
	@Transactional
	public ResponseDto<?> updateMyPage(MyPageRequestDto myPageRequestDto, String email) {
		Member member = getMember(email);

		//닉네임 중복 검사, 닉네임 변경
		//        if (!myPageRequsetDto.getNickName().equals(member.getNickName())) {
		//            return memberService.nickNameConfirm(myPageRequsetDto.getNickName());
		//        }
		//        //지역구 바꾸기
		//        Member memberRegion = memberRepository.findByMemberRegion();
		//        //프로필 사진 바꾸기
		//        Member memberPhoto = memberRepository.findByMemberPhoto();

		member.update(myPageRequestDto);
		//        memberRepository.save(member);
		return ResponseDto.success("수정이 완료되었습니다.");
	}
}
