package pl.maropce.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.maropce.myproject.appUser.AppUser;
import pl.maropce.myproject.appUser.AppUserService;

@Controller
public class RegisterController {

    private final AppUserService appUserService;

    public RegisterController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("appUser")AppUser appUser, Model model) {
        if (appUserService.existsByUsername(appUser.getUsername())) {
            model.addAttribute("error", true);
            return "register";
        } else {
            appUserService.addUser(appUser);
            return "redirect:/h2-console";
        }


    }
}
