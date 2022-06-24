package org.example.service.image;

import lombok.RequiredArgsConstructor;
import org.example.domain.image.ImageRepository;
import org.example.web.dto.image.ImageListResponseDto;
import org.example.web.dto.image.ImageSaveRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    //이미지 리뷰 매핑
    @Transactional
    public Long save(Long reviewCode, String imageId) {
        ImageSaveRequestDto requestDto = ImageSaveRequestDto.builder()
                .reviewCode(reviewCode)
                .imageId(imageId)
                .build();

        return imageRepository.save(requestDto.toEntity()).getImageCode();
    }

    //이미지 삭제
    @Transactional
    public void delete(Long reviewCode) {
        imageRepository.delete(reviewCode);
    }

    //이미지 출력
    @Transactional
    public List<ImageListResponseDto> findByReviewCode(Long reviewCode) {
        return imageRepository.findByReviewCode(reviewCode);
    }
}
