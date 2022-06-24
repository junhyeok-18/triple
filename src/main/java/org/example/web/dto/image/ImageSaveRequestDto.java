package org.example.web.dto.image;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.image.Image;

@Getter
@NoArgsConstructor
public class ImageSaveRequestDto {
    private Long reviewCode; //리뷰 일련번호
    private String imageId; //이미지 아이디

    @Builder
    public ImageSaveRequestDto(Long reviewCode, String imageId) {
        this.reviewCode = reviewCode;
        this.imageId = imageId;
    }

    public Image toEntity() {
        return Image.builder()
                .reviewCode(reviewCode)
                .imageId(imageId)
                .build();
    }
}
