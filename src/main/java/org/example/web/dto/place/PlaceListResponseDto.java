package org.example.web.dto.place;

import lombok.Getter;
import org.example.domain.place.Place;
import org.example.config.UuidFormatConfig;

import java.time.LocalDateTime;

@Getter
public class PlaceListResponseDto {
    private Long placeCode; //장소 일련번호
    private String placeId; //장소 아이디
    private String placeName; //장소 이름
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일

    public PlaceListResponseDto(Place entity) {
        this.placeCode = entity.getPlaceCode();
        this.placeId = UuidFormatConfig.decrypt(entity.getPlaceId());
        this.placeName = entity.getPlaceName();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
