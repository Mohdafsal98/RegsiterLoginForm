package com.example.RegisterLoginForm.controller;


import com.example.RegisterLoginForm.exception.ResourceNotFoundException;
import com.example.RegisterLoginForm.model.UsersModel;
import com.example.RegisterLoginForm.repository.UsersRepository;
import com.example.RegisterLoginForm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UsersController {

    @Autowired
   private final UsersService usersService;

    @Autowired
     private UsersRepository usersRepository;

    public UsersController(UsersService usersService, UsersRepository usersRepository) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel, Model model) {
        System.out.println("register request: " + usersModel);
        if (!usersModel.getPassword().equals(usersModel.getConfirmPassword())) {
            model.addAttribute("passwordError", "Passwords do not match");
            return "register_page"; // Return to the registration page with an error message
        }
        UsersModel registeredUser = usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());


        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model) {
        System.out.println("login request: " + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if (authenticated != null) {
            model.addAttribute("userLogin", authenticated.getLogin());
            model.addAttribute("userEmail", authenticated.getEmail());

            return "personal_page";
        } else {
            return "error_page";
        }

    }
        @PostMapping("/update/{id}")
        public UsersModel updateUserDetails(@RequestBody UsersModel usersModel){

        return usersService.updateDetails(usersModel);
        }

    }

