package com.kos.controller;

import com.kos.dto.UserDto;
import com.kos.entity.User;
import com.kos.exception.UserAlreadyExistsException;
import com.kos.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class registrationController {
    private final UserService userService;

    public registrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }
    @PostMapping("/user/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, HttpServletRequest request, Errors errors, Model model){
        if(errors.hasErrors()){
            return "registration";
        }
        try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistsException uaeEx) {
            model.addAttribute("message", "An account for that username/email already exists.");
            return "registration";
        }
        return "registrationSuccess";
    }
    @PostMapping("/user/registration")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult,
            Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("message","Something went wrong");
            return "registration";
        }
        try {
          User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistsException uaeEx) {
            model.addAttribute("message", "An account for that username/email already exists.");
            return "registration";
        }

        return "registrationSuccess";
    }
}
