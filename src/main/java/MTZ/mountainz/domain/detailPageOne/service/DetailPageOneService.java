package MTZ.mountainz.domain.detailPageOne.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import MTZ.mountainz.domain.detailPageOne.dto.response.DetailPageOneResponseDto;
import MTZ.mountainz.domain.like.repository.LikesRepository;
import MTZ.mountainz.domain.mountain.entity.Mountain;
import MTZ.mountainz.domain.mountain.repository.MountainRepository;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetailPageOneService {
	private final MountainRepository mountainRepository;
	private final LikesRepository likesRepository;

	// 상세페이지1 정보 불러오기
	public ResponseDto<?> detailPageOneList() {
		// 산목록에서 이름, 산이미지, 산퀴즈 true/false
		List<Mountain> mountainList = mountainRepository.findAll();
		List<DetailPageOneResponseDto> detailPageOneResponseDtoList = new ArrayList<>();

		for (Mountain mountain : mountainList) {
			detailPageOneResponseDtoList.add(
				DetailPageOneResponseDto.builder()
					.mountainName(mountain.getMountainName())
					.mountainImg(mountain.getMountainImg())
					.mountainQuiz(mountain.getMountainQuiz())
					// 산 id를 받아서 좋아요 수 반환해서 산 좋아요 count에 + 해주기
					.mountainLikeTotal(likesRepository.countAllByMountainId(mountain.getId()))
					.build()
			);
		}

		return ResponseDto.success(detailPageOneResponseDtoList);
	}
}
