package org.example.service.point;

import lombok.RequiredArgsConstructor;
import org.example.domain.point.PointRepository;
import org.example.web.dto.point.PointListResponseDto;
import org.example.web.dto.point.PointSaveRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PointService {
    private final PointRepository pointRepository;

    //포인트 생성
    @Transactional
    public Long save(PointSaveRequestDto requestDto) {
        return pointRepository.save(requestDto.toEntity()).getPointCode();
    }

    //포인트 출력
    @Transactional
    public List<PointListResponseDto> findByUserCode(Long userCode) {
        return pointRepository.findByUserCode(userCode);
    }

    //포인트 삭제 출력
    @Transactional
    public List<PointListResponseDto> pointDownList(Long userCode, Long reviewCode) {
        return pointRepository.pointDownList(userCode, reviewCode);
    }
}
