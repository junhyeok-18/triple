package org.example.domain.image;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"imageId"})})
public class Image extends BaseTimeEntity { //리뷰 이미지
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageCode; //이미지 일련번호

    @Column(nullable = false)
    private Long reviewCode; //리뷰 일련번호

    @Column(nullable = false)
    private String imageId; //이미지 아이디

    @Builder
    public Image(Long reviewCode, String imageId) {
        this.reviewCode = reviewCode;
        this.imageId = imageId;
    }
}
