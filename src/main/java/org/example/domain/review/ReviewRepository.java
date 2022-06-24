package org.example.domain.review;

import org.example.web.dto.review.ReviewListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //리뷰 출력
    @Query("SELECT r.reviewCode AS reviewCode, r.reviewId AS reviewId, r.content AS content, r.createdDate AS createdDate, r.modifiedDate AS modifiedDate, " +
           "p.placeCode AS placeCode, p.placeId AS placeId, p.placeName AS placeName, " +
           "u.userCode AS userCode, u.userId AS userId, u.userName AS userName, u.point AS point " +
           "FROM Review r " +
           "JOIN FETCH PlaceReview pr ON pr.reviewCode = r.reviewCode " +
           "JOIN FETCH Place p ON p.placeCode = pr.placeCode " +
           "JOIN FETCH UserReview ur ON ur.reviewCode = r.reviewCode " +
           "JOIN FETCH User u ON u.userCode = ur.userCode " +
           "WHERE pr.placeCode = :placeCode AND r.activation = 'Y' " +
           "GROUP BY reviewCode, reviewId, content, createdDate, modifiedDate, " +
           "placeCode, placeId, placeName, " +
           "userCode, userId, userName, point " +
           "ORDER BY r.reviewCode")
    List<Map<String, Object>> findByPlaceCode(@Param("placeCode") Long placeCode);

    //리뷰 상세 출력
    @Query("SELECT r.reviewCode AS reviewCode, r.reviewId AS reviewId, r.content AS content, r.createdDate AS createdDate, r.modifiedDate AS modifiedDate, " +
            "p.placeCode AS placeCode, p.placeId AS placeId, p.placeName AS placeName, " +
            "u.userCode AS userCode, u.userId AS userId, u.userName AS userName, u.point AS point " +
            "FROM Review r " +
            "JOIN FETCH PlaceReview pr ON pr.reviewCode = r.reviewCode " +
            "JOIN FETCH Place p ON p.placeCode = pr.placeCode " +
            "JOIN FETCH UserReview ur ON ur.reviewCode = r.reviewCode " +
            "JOIN FETCH User u ON u.userCode = ur.userCode " +
            "WHERE r.reviewCode = :reviewCode AND r.activation = 'Y' " +
            "GROUP BY reviewCode, reviewId, content, createdDate, modifiedDate, " +
            "placeCode, placeId, placeName, " +
            "userCode, userId, userName, point ")
    List<Map<String, Object>> findByReviewCode(@Param("reviewCode") Long reviewCode);

    //리뷰 아이디 검색
    @Query("SELECT r FROM Review r " +
            "WHERE r.reviewId = :reviewId")
    ReviewListResponseDto findByReviewId(@Param("reviewId") String reviewId);

    //리뷰 수정
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Review r SET r.content = :content " +
            "WHERE r.reviewCode = :reviewCode")
    void update(@Param("content") String content, @Param("reviewCode") Long reviewCode);

    //리뷰 삭제
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Review r SET r.activation = 'N' " +
           "WHERE r.reviewCode = :reviewCode")
    void delete(@Param("reviewCode") Long reviewCode);
}
