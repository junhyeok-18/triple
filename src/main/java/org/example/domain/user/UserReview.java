package org.example.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userCode", "reviewCode"})})
public class UserReview extends BaseTimeEntity { //사용자 리뷰 매핑
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userReviewCode; //사용자 리뷰 일련번호

    @Column(nullable = false)
    private Long userCode; //사용자 일련번호

    @Column(nullable = false)
    private Long reviewCode; //리뷰 일련번호

    @Builder
    public UserReview(Long userCode, Long reviewCode) {
        this.userCode = userCode;
        this.reviewCode = reviewCode;
    }
}
