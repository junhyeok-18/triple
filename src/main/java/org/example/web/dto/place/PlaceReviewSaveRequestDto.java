package org.example.web.dto.place;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.place.PlaceReview;

@Getter
@NoArgsConstructor
public class PlaceReviewSaveRequestDto {
    private Long placeCode; //장소 일련번호
    private Long reviewCode; //리뷰 일련번호

    @Builder
    public PlaceReviewSaveRequestDto(Long placeCode, Long reviewCode) {
        this.placeCode = placeCode;
        this.reviewCode = reviewCode;
    }

    public PlaceReview toEntity() {
        return PlaceReview.builder()
                .placeCode(placeCode)
                .reviewCode(reviewCode)
                .build();
    }
}
