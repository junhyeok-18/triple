package org.example.domain.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"reviewId"})})
public class Review extends BaseTimeEntity { //리뷰
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewCode; //리뷰 일련번호

    @Column(nullable = false)
    private String reviewId; //리뷰 아이디

    @Column(nullable = false)
    private String content; //리뷰 내용

    @Column(nullable = false, columnDefinition = "varchar(1) default 'Y'")
    private String activation; //리뷰 삭제 유무

    @Builder
    public Review(String reviewId, String content, String activation) {
        this.reviewId = reviewId;
        this.content = content;
        this.activation = activation;
    }
}
