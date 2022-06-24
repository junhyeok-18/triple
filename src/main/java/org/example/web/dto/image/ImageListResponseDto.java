package org.example.web.dto.image;

import lombok.Getter;
import org.example.domain.image.Image;

@Getter
public class ImageListResponseDto {
    private Long imageCode; //이미지 일련번호
    private Long reviewCode; //리뷰 일련번호
    private String imageId; //이미지 아이디

    public ImageListResponseDto(Image entity) {
        this.imageCode = entity.getImageCode();
        this.reviewCode = entity.getReviewCode();
        this.imageId = entity.getImageId();
    }
}
