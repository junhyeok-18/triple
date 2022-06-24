package org.example.domain.user;

import org.example.web.dto.user.UserListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    //사용자 아이디 검색
    @Query("SELECT u FROM User u " +
           "WHERE u.userId = :userId")
    UserListResponseDto findByUserId(@Param("userId") String userId);

    //사용자 포인트 수정
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.point = :point " +
            "WHERE u.userCode = :userCode")
    void update(@Param("point") String point, @Param("userCode") Long userCode);
}
