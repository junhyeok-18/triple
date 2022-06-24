package org.example.service.user;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.UserRepository;
import org.example.domain.user.UserReviewRepository;
import org.example.web.dto.user.UserListResponseDto;
import org.example.web.dto.user.UserReviewSaveRequestDto;
import org.example.web.dto.user.UserSaveRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserReviewRepository userReviewRepository;

    //사용자 생성
    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getUserCode();
    }

    //사용자 출력
    @Transactional
    public List<UserListResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserListResponseDto::new)
                .collect(Collectors.toList());
    }

    //사용자 아이디 검색
    @Transactional
    public UserListResponseDto findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    //사용자 리뷰 매핑
    @Transactional
    public Long userReviewMap(Long userCode, Long reviewCode) {
        UserReviewSaveRequestDto requestDto = UserReviewSaveRequestDto.builder()
                .userCode(userCode)
                .reviewCode(reviewCode)
                .build();

        return userReviewRepository.save(requestDto.toEntity()).getUserReviewCode();
    }

    //사용자 포인트 수정
    @Transactional
    public void update(String point, Long userCode) {
        userRepository.update(point, userCode);
    }
}
