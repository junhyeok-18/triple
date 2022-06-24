package org.example.web.api;

import lombok.RequiredArgsConstructor;
import org.example.config.SessionUser;
import org.example.config.UuidFormatConfig;
import org.example.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthApiController {
    private final UserService userService;
    private final HttpSession httpSession;

    //사용자 로그인
    @PostMapping("/api/v1/user/login")
    public void userLogin(@RequestBody Map<String, Object> map) {
        final String[] userIdData = {""};

        map.forEach((key, value) -> {
            switch (key) {
                case "userId":
                    userIdData[0] = value.toString();
                    break;
            }
        });

        String userId = UuidFormatConfig.encrypt(userIdData[0]);
        httpSession.setAttribute("user", new SessionUser(userService.findByUserId(userId)));
    }
}
