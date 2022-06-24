package org.example.service.place;

import lombok.RequiredArgsConstructor;
import org.example.config.UuidFormatConfig;
import org.example.domain.place.PlaceRepository;
import org.example.domain.place.PlaceReviewRepository;
import org.example.web.dto.place.PlaceListResponseDto;
import org.example.web.dto.place.PlaceReviewSaveRequestDto;
import org.example.web.dto.place.PlaceSaveRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceReviewRepository placeReviewRepository;

    //장소 생성
    @Transactional
    public Long save(PlaceSaveRequestDto requestDto) {
        return placeRepository.save(requestDto.toEntity()).getPlaceCode();
    }

    //장소 출력
    @Transactional
    public List<PlaceListResponseDto> findAll() {
        return placeRepository.findAll().stream()
                .map(PlaceListResponseDto::new)
                .collect(Collectors.toList());
    }

    //장소 아이디 검색
    @Transactional
    public PlaceListResponseDto findByPlaceId(String placeId) {
        return placeRepository.findByPlaceId(UuidFormatConfig.encrypt(placeId));
    }

    //장소 리뷰 매핑
    @Transactional
    public Long placeReviewMap(Long placeCode, Long reviewCode) {
        PlaceReviewSaveRequestDto requestDto = PlaceReviewSaveRequestDto.builder()
                .placeCode(placeCode)
                .reviewCode(reviewCode)
                .build();

        return placeReviewRepository.save(requestDto.toEntity()).getPlaceReviewCode();
    }
}
