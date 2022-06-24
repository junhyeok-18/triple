package org.example.domain.place;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"placeCode", "reviewCode"})})
public class PlaceReview  extends BaseTimeEntity { //장소 리뷰 매핑
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeReviewCode; //장소 리뷰 일련번호

    @Column(nullable = false)
    private Long placeCode; //장소 일련번호

    @Column(nullable = false)
    private Long reviewCode; //리뷰 일련번호

    @Builder
    public PlaceReview(Long placeCode, Long reviewCode) {
        this.placeCode = placeCode;
        this.reviewCode = reviewCode;
    }
}
