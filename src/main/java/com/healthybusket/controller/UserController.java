package com.healthybusket.controller;

import com.healthybusket.domen.Role;
import com.healthybusket.domen.User;
import com.healthybusket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userLst(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("id") User user
    ) {

      userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("profile")//для просмотретра данных пользователя
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username", user.getUsername());
        model.addAttribute("phone", user.getPhonenumber());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("numberofpasport", user.getNumberofpasport());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("profile")//для сохранения профайла
        public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam String numberofpasport,
            @RequestParam String email
            ){
        userService.updateProfile(user, password, phone, address, numberofpasport, email);
        return "redirect:/user/profile";
    }

}
