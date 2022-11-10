package MTZ.mountainz.domain.myPage.service;


import MTZ.mountainz.domain.member.entity.Member;
import MTZ.mountainz.domain.member.repository.MemberRepository;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;


//    //마이 페이지 정보 불러오기
//    @Transactional(readOnly = true)
//    public ResponseDto<?> getMyPageList( ) {
//
//
//        return ResponseDto.success();
//    }
//    //마이 페이지 수정
//    @Transactional(readOnly = true)
//    public ResponseDto<?> updateMyPage(String email, ) {
//        Member member = get
//
//
//
//
//        //닉네임 바꾸기
//        //프로필 사진 바꾸기
//        //지역구 바꾸기
//        member.update();
//        memberRepository.save(member);
//        return ResponseDto.success();
//    }
}
