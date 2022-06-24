package org.example.domain.point;

import org.example.web.dto.point.PointListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    //포인트 출력
    @Query("SELECT p FROM Point p " +
           "WHERE p.userCode = :userCode " +
           "ORDER BY p.pointCode")
    List<PointListResponseDto> findByUserCode(@Param("userCode") Long userCode);

    //포인트 삭제 출력
    @Query("SELECT p FROM Point p " +
           "WHERE p.userCode = :userCode AND p.reviewCode = :reviewCode " +
           "ORDER BY p.pointCode")
    List<PointListResponseDto> pointDownList(@Param("userCode") Long userCode, @Param("reviewCode") Long reviewCode);
}
