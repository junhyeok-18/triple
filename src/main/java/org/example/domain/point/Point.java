package org.example.domain.point;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Point extends BaseTimeEntity { //포인트 증감 내역
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointCode; //포인트 일련번호

    @Column(nullable = false)
    private Long userCode; //사용자 일련번호

    @Column(nullable = false)
    private Long reviewCode; //리뷰 일련번호

    @Column(nullable = false)
    private String point; //포인트

    @Column(nullable = false)
    private String updown; //증감 구분 (U : 증가, D : 감소)

    @Column(nullable = false)
    private String reason; //증감 사유

    @Builder
    public Point(Long userCode, Long reviewCode, String point, String updown, String reason) {
        this.userCode = userCode;
        this.reviewCode = reviewCode;
        this.point = point;
        this.updown = updown;
        this.reason = reason;
    }
}
