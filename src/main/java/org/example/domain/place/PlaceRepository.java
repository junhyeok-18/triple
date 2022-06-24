package org.example.domain.place;

import org.example.web.dto.place.PlaceListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    //장소 아이디 검색
    @Query("SELECT p FROM Place p " +
           "WHERE p.placeId = :placeId")
    PlaceListResponseDto findByPlaceId(@Param("placeId") String placeId);
}
