package org.example.web.api;

import lombok.RequiredArgsConstructor;
import org.example.service.place.PlaceService;
import org.example.web.dto.place.PlaceSaveRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlaceApiController {
    private final PlaceService placeService;

    //장소 생성
    @PostMapping("/api/v1/place")
    public Long save(@RequestBody PlaceSaveRequestDto requestDto) {
        return placeService.save(requestDto);
    }
}
