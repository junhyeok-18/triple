package org.example.web.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.config.UuidFormatConfig;
import org.example.domain.review.Review;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {
    private String reviewId; //리뷰 아이디
    private String content; //리뷰 내용
    private String activation; //리뷰 삭제 유무

    @Builder
    public ReviewSaveRequestDto(String reviewId, String content, String activation) {
        this.reviewId = reviewId;
        this.content = content;
        this.activation = activation;
    }

    public Review toEntity() {
        return Review.builder()
                .reviewId(UuidFormatConfig.encrypt(reviewId))
                .content(content)
                .activation("Y")
                .build();
    }
}
