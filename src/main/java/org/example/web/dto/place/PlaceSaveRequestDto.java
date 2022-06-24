package org.example.web.dto.place;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.place.Place;
import org.example.config.UuidFormatConfig;

@Getter
@NoArgsConstructor
public class PlaceSaveRequestDto {
    private String placeId; //장소 아이디
    private String placeName; //장소 이름

    @Builder
    public PlaceSaveRequestDto(String placeId, String placeName) {
        this.placeId = placeId;
        this.placeName = placeName;
    }

    public Place toEntity() {
        return Place.builder()
                .placeId(UuidFormatConfig.encrypt(placeId))
                .placeName(placeName)
                .build();
    }
}
