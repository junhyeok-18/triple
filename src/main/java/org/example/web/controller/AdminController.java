package org.example.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.place.PlaceService;
import org.example.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final PlaceService placeService;
    private final UserService userService;

    //장소 출력 및 생성
    @GetMapping("/admin/place")
    public String adminPlace(Model model) {
        model.addAttribute("placeList", placeService.findAll());

        return "/admin/place";
    }

    //사용자 출력 및 생성
    @GetMapping("/admin/user")
    public String adminUser(Model model) {
        model.addAttribute("userList", userService.findAll());

        return "/admin/user";
    }
}
