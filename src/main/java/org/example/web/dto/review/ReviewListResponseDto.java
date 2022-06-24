package org.example.web.dto.review;

import lombok.Getter;
import org.example.config.UuidFormatConfig;
import org.example.domain.review.Review;

import java.time.LocalDateTime;

@Getter
public class ReviewListResponseDto {
    private Long reviewCode; //리뷰 일련번호
    private String reviewId; //리뷰 아이디
    private String content; //리뷰 내용
    private String activation; //리뷰 삭제 유무
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일

    public ReviewListResponseDto(Review entity) {
        this.reviewCode = entity.getReviewCode();
        this.reviewId = UuidFormatConfig.decrypt(entity.getReviewId());
        this.content = entity.getContent();
        this.activation = entity.getActivation();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
