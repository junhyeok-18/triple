package org.example.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.config.UuidFormatConfig;
import org.example.domain.user.User;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String userId; //사용자 아이디
    private String userName; //사용자 이름
    private String point; //사용자 포인트

    @Builder
    public UserSaveRequestDto(String userId, String userName, String point) {
        this.userId = userId;
        this.userName = userName;
        this.point = point;
    }

    public User toEntity() {
        return User.builder()
                .userId(UuidFormatConfig.encrypt(userId))
                .userName(userName)
                .point(point)
                .build();
    }
}
