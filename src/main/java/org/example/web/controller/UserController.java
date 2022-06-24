package org.example.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.config.LoginUser;
import org.example.config.SessionUser;
import org.example.config.UuidFormatConfig;
import org.example.service.place.PlaceService;
import org.example.service.point.PointService;
import org.example.service.review.ReviewService;
import org.example.service.user.UserService;
import org.example.web.dto.user.UserListResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final PlaceService placeService;
    private final UserService userService;
    private final ReviewService reviewService;
    private final PointService pointService;

    //사용자 출력
    @GetMapping("/")
    public String UserMain(Model model) {
        model.addAttribute("userList", userService.findAll());

        return "/user/user";
    }

    //장소 출력
    @GetMapping("/place")
    public String UserPlace(Model model, @LoginUser SessionUser user) {
        model.addAttribute("placeList", placeService.findAll());
        model.addAttribute("user", user);

        return "/user/place";
    }

    //리뷰 출력
    @GetMapping("/reviewList/{placeId}")
    public String UserReviewList(Model model, @PathVariable String placeId, @LoginUser SessionUser user) {
        Long placeCode = placeService.findByPlaceId(placeId).getPlaceCode();

        model.addAttribute("reviewList", reviewService.findByPlaceCode(placeCode));
        model.addAttribute("user", user);

        return "/user/reviewList";
    }

    //리뷰 작성
    @GetMapping("/reviewAdd/{placeId}")
    public String UserReviewAdd(Model model, @PathVariable String placeId, @LoginUser SessionUser user) {
        model.addAttribute("user", user);
        model.addAttribute("placeId", placeId);

        return "/user/reviewAdd";
    }

    //리뷰 수정
    @GetMapping("/reviewMod/{reviewId}")
    public String UserReviewMod(Model model, @PathVariable String reviewId, @LoginUser SessionUser user) {
        Long reviewCode = reviewService.findByReviewId(reviewId).getReviewCode();

        model.addAttribute("user", user);
        model.addAttribute("reviewInfo", reviewService.findByReviewCode(reviewCode));

        return "/user/reviewMod";
    }

    //포인트 출력
    @GetMapping("/point/{userId}")
    public String UserPointList(Model model, @PathVariable String userId) {
        //사용자 아이디 검색
        UserListResponseDto userListResponseDto = userService.findByUserId(UuidFormatConfig.encrypt(userId));
        Long userCode = userListResponseDto.getUserCode();
        String userName = userListResponseDto.getUserName();

        model.addAttribute("pointList", pointService.findByUserCode(userCode));
        model.addAttribute("userName", userName);

        return "/user/point";
    }
}
