package MTZ.mountainz.domain.mainPage.controller;


import MTZ.mountainz.domain.mainPage.service.MainPageService;
import MTZ.mountainz.domain.member.dto.request.MemberRequestDto;
import MTZ.mountainz.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainPageController {

    private final MainPageService mainPageService;


    //메인 페이지 정보 불러오기
    @GetMapping("/main")
    public ResponseDto<?> getMain() {
        return mainPageService.getMain();

    }
    //지역별 산 목록 불러오기
    @GetMapping("/mountainRegionList/{mountainRegion}")
    public ResponseDto<?> getRegionList(@PathVariable String mountainRegion) {
        return mainPageService.getRegionList(mountainRegion);
        }
    //태그 관련 목록 불러오기(난이도)
    @GetMapping("/mountainLevelList/{mountainLevel}")
    public ResponseDto<?> getLevelList(@PathVariable String mountainLevel) {
        return mainPageService.getLevelList(mountainLevel);
    }

    //태그 관련 목록 불러오기(계절)
    @GetMapping("/mountainSeasonList/{mountainSeason}")
    public ResponseDto<?> getSeasonList(@PathVariable String mountainSeason) {
        return mainPageService.getSeasonList(mountainSeason);
    }
}

