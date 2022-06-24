package org.example.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId"})})
public class User extends BaseTimeEntity { //사용자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCode; //사용자 일련번호

    @Column(nullable = false)
    private String userId; //사용자 아이디

    @Column(nullable = false)
    private String userName; //사용자 이름

    @Column(nullable = false, columnDefinition = "varchar(10) default '0'")
    private String point; //사용자 포인트

    @Builder
    public User(String userId, String userName, String point) {
        this.userId = userId;
        this.userName = userName;
        this.point = point;
    }
}
