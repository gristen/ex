package com.example.compl.Controllers;

import com.example.compl.Models.StatmentModel;
import com.example.compl.Models.UserModel;
import com.example.compl.Service.StatmentService;
import com.example.compl.Service.UserService;
import com.example.compl.repo.StatmentRepo;
import com.example.compl.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor

public class MainController {
    UserService userService;
    StatmentService service;

    StatmentRepo statmentRepo;
    UserRepo userRepo;



    @GetMapping("/")
    public String homepage(Model model, Principal principal){

        UserModel user = userRepo.findByUsername(principal.getName());

        model.addAttribute("stats", service.findByUser(user));
        model.addAttribute("username", principal.getName());
        return "home";


    }
    @GetMapping("/register")
    public String register(){

        return "register";
    }
    @GetMapping("/add")
    public String add(){

        return "add";
    }
    @GetMapping("/login")
    public String login(){

        return "login";
    }
    @GetMapping("/edit/{id}")
    public String edite(@PathVariable Long id,Model model){
        model.addAttribute("stat",statmentRepo.findById(id).orElse(null));
        model.addAttribute("statuses", service.getAllStatus());
        return "edit";
    }

    @GetMapping("/admin")
    public String adminpage(Model model, Principal principal){
        model.addAttribute("stats", statmentRepo.findAll());
        return "admin";
    }

    @PostMapping("/register")
    public String reg(@ModelAttribute UserModel data){
        userService.store(data);
        return "redirect:/";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute StatmentModel data){
        service.store(data);
        return "redirect:/";
    }
    @PostMapping("/edit")
    public String editt(@ModelAttribute StatmentModel data){
        service.edit(data);
        return "redirect:/";
    }


}
