package MTZ.mountainz.domain.detailPageOne.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import MTZ.mountainz.domain.detailPageOne.dto.request.FilterRequestDto;
import MTZ.mountainz.domain.detailPageOne.dto.request.KeywordRequestDto;
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

	// pageable test2
	// 상세페이지1 정보 불러오기
	// @Cacheable(value = "Mountain", key = "'detailOnePageAll'", cacheManager = "redisCacheManager")
	public ResponseDto<?> detailPageOneList(Pageable pageable) {
		// 산목록에서 이름, 산이미지, 산퀴즈 true/false
		Page<Mountain> mountainList = mountainRepository.findByMountainAll(pageable);
		List<DetailPageOneResponseDto> detailPageOneResponseDtoList = new ArrayList<>();

		for (Mountain mountain : mountainList) {
			detailPageOneResponseDtoList.add(
				DetailPageOneResponseDto.builder()
					.id(mountain.getId())
					.name(mountain.getName())
					.img(mountain.getImg())
					.quiz(mountain.getQuiz())
					.region(mountain.getRegion())
					.season(mountain.getSeason())
					.level(mountain.getLevel())
					.time(mountain.getTime())
					// 산 id를 받아서 좋아요 수 반환해서 산 좋아요 count에 + 해주기
					.mountainLikeTotal(likesRepository.countAllByMountainId(mountain.getId()))
					.build()
			);
		}

		return ResponseDto.success(detailPageOneResponseDtoList);
	}

	// 키워드 검색 (산이름만 추후 queryDSL로 전체 조회할 예정)
	@Transactional
	public ResponseDto<?> getKeywordSearch(KeywordRequestDto keywordRequestDto) {
		// List<Mountain> mountainSearchList = mountainRepository.findByName(keywordRequestDto.getKeyword());
		List<Mountain> mountainSearchList = mountainRepository.findByKeyword(keywordRequestDto.getKeyword());
		List<DetailPageOneResponseDto> detailPageOneResponseDtoList = new ArrayList<>();
		for (Mountain mountain : mountainSearchList) {
			detailPageOneResponseDtoList.add(
				DetailPageOneResponseDto.builder()
					.id(mountain.getId())
					.name(mountain.getName())
					.img(mountain.getImg())
					.quiz(mountain.getQuiz())
					.region(mountain.getRegion())
					.season(mountain.getSeason())
					.level(mountain.getLevel())
					.time(mountain.getTime())
					// 산 id를 받아서 좋아요 수 반환해서 산 좋아요 count에 + 해주기
					.mountainLikeTotal(likesRepository.countAllByMountainId(mountain.getId()))
					.build()
			);
		}

		return ResponseDto.success(detailPageOneResponseDtoList);
	}

	// 필터 검색
	@Transactional
	public ResponseDto<?> getFilterSearch(FilterRequestDto filterRequestDto) {
		// List<Mountain> mountainFilterList = mountainRepository.findByRegionAndSeasonAndLevelAndTime(
		// 	filterRequestDto.getRegion(), filterRequestDto.getSeason(),
		// 	filterRequestDto.getLevel(), filterRequestDto.getTime()
		// );
		// 상세1의 모든 정보 넣어 보내기 res
		List<Mountain> mountainFilterList = mountainRepository.findByMountainFilter(filterRequestDto);
		List<DetailPageOneResponseDto> detailPageOneResponseDtoList = new ArrayList<>();

		for (Mountain mountain : mountainFilterList) {
			detailPageOneResponseDtoList.add(
				DetailPageOneResponseDto.builder()
					.id(mountain.getId())
					.name(mountain.getName())
					.img(mountain.getImg())
					.quiz(mountain.getQuiz())
					.region(mountain.getRegion())
					.season(mountain.getSeason())
					.level(mountain.getLevel())
					.time(mountain.getTime())
					// 산 id를 받아서 좋아요 수 반환해서 산 좋아요 count에 + 해주기
					.mountainLikeTotal(likesRepository.countAllByMountainId(mountain.getId()))
					.build()
			);
		}

		return ResponseDto.success(detailPageOneResponseDtoList);
	}
}
