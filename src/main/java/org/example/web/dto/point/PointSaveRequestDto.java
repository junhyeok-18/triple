package org.example.web.dto.point;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.point.Point;

@Getter
@NoArgsConstructor
public class PointSaveRequestDto {
    private Long userCode; //사용자 일련번호
    private Long reviewCode; //리뷰 일련번호
    private String point; //포인트
    private String updown; //증감 구분 (U : 증가, D : 감소)
    private String reason; //증감 사유

    @Builder
    public PointSaveRequestDto(Long userCode, Long reviewCode, String point, String updown, String reason) {
        this.userCode = userCode;
        this.reviewCode = reviewCode;
        this.point = point;
        this.updown = updown;
        this.reason = reason;
    }

    public Point toEntity() {
        return Point.builder()
                .userCode(userCode)
                .reviewCode(reviewCode)
                .point(point)
                .updown(updown)
                .reason(reason)
                .build();
    }
}
