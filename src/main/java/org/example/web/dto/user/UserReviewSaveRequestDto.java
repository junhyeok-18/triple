package org.example.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.user.UserReview;

@Getter
@NoArgsConstructor
public class UserReviewSaveRequestDto {
    private Long userCode; //사용자 일련번호
    private Long reviewCode; //리뷰 일련번호

    @Builder
    public UserReviewSaveRequestDto(Long userCode, Long reviewCode) {
        this.userCode = userCode;
        this.reviewCode = reviewCode;
    }

    public UserReview toEntity() {
        return UserReview.builder()
                .userCode(userCode)
                .reviewCode(reviewCode)
                .build();
    }
}
