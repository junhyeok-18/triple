package org.example.service.review;

import lombok.RequiredArgsConstructor;
import org.example.config.UuidFormatConfig;
import org.example.domain.review.Review;
import org.example.domain.review.ReviewRepository;
import org.example.web.dto.review.ReviewListResponseDto;
import org.example.web.dto.review.ReviewSaveRequestDto;
import org.example.web.dto.review.ReviewUpdateRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    //리뷰 생성
    @Transactional
    public Long save(ReviewSaveRequestDto requestDto) {
        return reviewRepository.save(requestDto.toEntity()).getReviewCode();
    }

    //리뷰 출력
    @Transactional
    public List<Map<String, Object>> findByPlaceCode(Long placeCode) {
        return reviewRepository.findByPlaceCode(placeCode);
    }

    //리뷰 상세 출력
    @Transactional
    public List<Map<String, Object>> findByReviewCode(Long reviewCode) {
        return reviewRepository.findByReviewCode(reviewCode);
    }

    //리뷰 아이디 검색
    @Transactional
    public ReviewListResponseDto findByReviewId(String reviewId) {
        return reviewRepository.findByReviewId(UuidFormatConfig.encrypt(reviewId));
    }

    //리뷰 수정
    @Transactional
    public void update(Long reviewCode, String content) {
        reviewRepository.update(content, reviewCode);
    }

    //리뷰 삭제
    @Transactional
    public void delete(Long reviewCode) {
        reviewRepository.delete(reviewCode);
    }
}
