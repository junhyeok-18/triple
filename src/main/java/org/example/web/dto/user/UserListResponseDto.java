package org.example.web.dto.user;

import lombok.Getter;
import org.example.config.UuidFormatConfig;
import org.example.domain.user.User;

import java.time.LocalDateTime;

@Getter
public class UserListResponseDto {
    private Long userCode; //사용자 일련번호
    private String userId; //사용자 아이디
    private String userName; //사용자 이름
    private String point; //사용자 포인트
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일

    public UserListResponseDto(User entity) {
        this.userCode = entity.getUserCode();
        this.userId = UuidFormatConfig.decrypt(entity.getUserId());
        this.userName = entity.getUserName();
        this.point = entity.getPoint();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
