package org.example.config;

import lombok.Getter;
import org.example.web.dto.user.UserListResponseDto;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Long userCode; //사용자 일련번호
    private String userId; //사용자 아이디
    private String userName; //사용자 이름
    private String point; //사용자 포인트

    public SessionUser(UserListResponseDto entity) {
        this.userCode = entity.getUserCode();
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
        this.point = entity.getPoint();
    }
}
