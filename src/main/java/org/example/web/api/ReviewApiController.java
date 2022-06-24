package org.example.web.api;

import lombok.RequiredArgsConstructor;
import org.example.config.LoginUser;
import org.example.config.SessionUser;
import org.example.config.UuidFormatConfig;
import org.example.service.image.ImageService;
import org.example.service.place.PlaceService;
import org.example.service.point.PointService;
import org.example.service.review.ReviewService;
import org.example.service.user.UserService;
import org.example.web.dto.image.ImageListResponseDto;
import org.example.web.dto.point.PointListResponseDto;
import org.example.web.dto.point.PointSaveRequestDto;
import org.example.web.dto.review.ReviewSaveRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {
    private final ReviewService reviewService;
    private final PlaceService placeService;
    private final UserService userService;
    private final ImageService imageService;
    private final PointService pointService;
    private final HttpSession httpSession;

    //리뷰 이벤트
    @PostMapping("/events")
    public void events(@RequestBody Map<String, Object> map, @LoginUser SessionUser user) {
        final String[] typeData = {""};
        final String[] actionData = {""};
        final String[] reviewIdData = {""};
        final String[] contentData = {""};
        final String[] attachedPhotoIdsData = {""};
        final String[] userIdData = {""};
        final String[] placeIdData = {""};
        int count = 0;
        String imgChk = "N";

        map.forEach((key, value) -> {
            switch (key) {
                case "type":
                    typeData[0] = value.toString();
                    break;
                case "action":
                    actionData[0] = value.toString();
                    break;
                case "reviewId":
                    reviewIdData[0] = value.toString();
                    break;
                case "content":
                    contentData[0] = value.toString();
                    break;
                case "attachedPhotoIds":
                    attachedPhotoIdsData[0] = value.toString();
                    break;
                case "userId":
                    userIdData[0] = value.toString();
                    break;
                case "placeId":
                    placeIdData[0] = value.toString();
                    break;
            }
        });

        String type = typeData[0];
        String action = actionData[0];
        String reviewId = reviewIdData[0];
        String content = contentData[0];
        String attachedPhotoIds = attachedPhotoIdsData[0];
        String userId = UuidFormatConfig.encrypt(userIdData[0]);
        String placeId = placeIdData[0];
        PointSaveRequestDto pointSave;

        if("REVIEW".equals(type)) {
            //리뷰 작성
            Long userCode = user.getUserCode();
            if ("ADD".equals(action)) {
                Long placeCode = placeService.findByPlaceId(placeId).getPlaceCode();
                List<Map<String, Object>> list = reviewService.findByPlaceCode(placeCode);

                ReviewSaveRequestDto requestDto = ReviewSaveRequestDto.builder()
                        .reviewId(reviewId)
                        .content(content)
                        .activation("Y")
                        .build();

                Long reviewCode = reviewService.save(requestDto);
                if (list.size() > 0) {
                } else {
                    count++; //특정 장소에 첫 리뷰 작성 1점
                    pointSave = PointSaveRequestDto.builder()
                            .userCode(user.getUserCode())
                            .reviewCode(reviewCode)
                            .point("1")
                            .updown("U")
                            .reason("특정 장소에 첫 리뷰 작성")
                            .build();
                    pointService.save(pointSave);
                }

                count++; //1자 이상 텍스트 작성 1점
                pointSave = PointSaveRequestDto.builder()
                        .userCode(user.getUserCode())
                        .reviewCode(reviewCode)
                        .point("1")
                        .updown("U")
                        .reason("1자 이상 텍스트 작성")
                        .build();
                pointService.save(pointSave);

                //장소 리뷰 매핑
                placeService.placeReviewMap(placeCode, reviewCode);

                //사용자 리뷰 매핑
                userService.userReviewMap(userCode, reviewCode);

                //이미지 리뷰 매핑
                attachedPhotoIds = attachedPhotoIds.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "");
                String[] imageIdList = attachedPhotoIds.split(",");

                for (int i = 0; i < imageIdList.length; i++) {
                    String imageId = imageIdList[i];
                    if (imageId.length() > 0) {
                        imageService.save(reviewCode, imageId);
                        imgChk = "Y";
                    }
                }

                if ("Y".equals(imgChk)) {
                    count++; //1장 이상 사진 첨부 1점
                    pointSave = PointSaveRequestDto.builder()
                            .userCode(user.getUserCode())
                            .reviewCode(reviewCode)
                            .point("1")
                            .updown("U")
                            .reason("1장 이상 사진 첨부")
                            .build();
                    pointService.save(pointSave);
                }

                int point = Integer.parseInt(userService.findByUserId(userId).getPoint());
                if(point <= count) {
                    point = count;
                } else {
                    point = point - count;
                }

                //사용자 포인트 수정
                userService.update(String.valueOf(point), userCode);
            }
            //리뷰 수정
            else if ("MOD".equals(action)) {
                Long reviewCode = reviewService.findByReviewId(reviewId).getReviewCode();

                //리뷰 수정
                reviewService.update(reviewCode, content);

                //이미지 출력
                String check = "N";
                List<ImageListResponseDto> list = imageService.findByReviewCode(reviewCode);
                if(list.size() > 0) {
                    check = "Y";
                }

                //이미지 삭제
                imageService.delete(reviewCode);

                //이미지 리뷰 매핑
                attachedPhotoIds = attachedPhotoIds.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "");
                String[] imageIdList = attachedPhotoIds.split(",");

                for (int i = 0; i < imageIdList.length; i++) {
                    String imageId = imageIdList[i];
                    if (imageId.length() > 0) {
                        imageService.save(reviewCode, imageId);
                        imgChk = "Y";
                    }
                }

                //작성된 리뷰에 이미지가 없었고 리뷰 수정 시에 이미지를 추가했을 때 +1
                if ("N".equals(check) && "Y".equals(imgChk)) {
                    count++; //1장 이상 사진 첨부 1점
                    pointSave = PointSaveRequestDto.builder()
                            .userCode(user.getUserCode())
                            .reviewCode(reviewCode)
                            .point("1")
                            .updown("U")
                            .reason("1장 이상 사진 첨부")
                            .build();
                    pointService.save(pointSave);
                }
                //작성된 리뷰에 이미지가 있었고 리뷰 수정 시에 이미지를 삭제했을 때 -1
                else if("Y".equals(check) && "N".equals(imgChk)) {
                    count--;
                    pointSave = PointSaveRequestDto.builder()
                            .userCode(user.getUserCode())
                            .reviewCode(reviewCode)
                            .point("1")
                            .updown("D")
                            .reason("첨부된 사진 삭제")
                            .build();
                    pointService.save(pointSave);
                }

                int point = Integer.parseInt(userService.findByUserId(userId).getPoint());
                point = point + (count);

                //사용자 포인트 수정
                userService.update(String.valueOf(point), userCode);
            }
            //리뷰 삭제
            else if ("DELETE".equals(action)) {
                Long reviewCode = reviewService.findByReviewId(reviewId).getReviewCode();
                reviewService.delete(reviewCode);

                List<PointListResponseDto> pointListResponseDtos = pointService.pointDownList(userCode, reviewCode);
                for(PointListResponseDto list : pointListResponseDtos) {
                    System.out.println(list.getUpdown() + " / " + list.getPoint());
                    if("U".equals(list.getUpdown())) {
                        count += Integer.parseInt(list.getPoint());
                    } else {
                        count -= Integer.parseInt(list.getPoint());
                    }
                }

                //포인트 수정
                pointSave = PointSaveRequestDto.builder()
                        .userCode(user.getUserCode())
                        .reviewCode(reviewCode)
                        .point(String.valueOf(count))
                        .updown("D")
                        .reason("리뷰 삭제")
                        .build();
                pointService.save(pointSave);

                int point = Integer.parseInt(userService.findByUserId(userId).getPoint());
                point = point - count;

                //사용자 포인트 수정
                userService.update(String.valueOf(point), userCode);
            }
        }
    }
}
