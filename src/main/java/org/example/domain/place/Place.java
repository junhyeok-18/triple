package org.example.domain.place;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"placeId"})})
public class Place extends BaseTimeEntity { //장소
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeCode; //장소 일련번호

    @Column(nullable = false)
    private String placeId; //장소 아이디

    @Column(nullable = false)
    private String placeName; //장소 이름

    @Builder
    public Place(String placeId, String placeName) {
        this.placeId = placeId;
        this.placeName = placeName;
    }
}
