package org.example.web.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {
    private String content; //리뷰 내용

    @Builder
    public ReviewUpdateRequestDto(String content) {
        this.content = content;
    }
}
