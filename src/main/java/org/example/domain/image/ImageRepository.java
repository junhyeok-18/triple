package org.example.domain.image;

import org.example.web.dto.image.ImageListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    //이미지 삭제
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Image i " +
           "WHERE i.reviewCode = :reviewCode")
    void delete(@Param("reviewCode") Long reviewCode);

    //이미지 출력
    @Query("SELECT i FROM Image i " +
           "WHERE i.reviewCode = :reviewCode")
    List<ImageListResponseDto> findByReviewCode(@Param("reviewCode") Long reviewCode);
}
