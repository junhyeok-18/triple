package org.example.web.dto.point;

import lombok.Getter;
import org.example.domain.point.Point;

import java.time.LocalDateTime;

@Getter
public class PointListResponseDto {
    private Long pointCode; //포인트 일련번호
    private Long userCode; //사용자 일련번호
    private Long reviewCode; //리뷰 일련번호
    private String point; //포인트
    private String updown; //증감 구분 (U : 증가, D : 감소)
    private String reason; //증감 사유
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일

    public PointListResponseDto(Point entity) {
        this.pointCode = entity.getPointCode();
        this.userCode = entity.getUserCode();
        this.reviewCode = entity.getReviewCode();
        this.point = entity.getPoint();
        this.updown = entity.getUpdown();
        this.reason = entity.getReason();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
