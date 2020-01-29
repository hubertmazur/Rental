package pl.mazur.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mazur.rental.model.User;
import pl.mazur.rental.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {

    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login/form")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("loginError", true);

        if (logout != null)
            model.addAttribute("logoutMessage", true);
        return "login";
    }


    @GetMapping("registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("registration")
    public String registration(@ModelAttribute("userForm") @Valid User userForm, Model model, BindingResult bindingResult, HttpServletRequest request, Errors error) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", true);
            return "registration";
        }
        userService.save(userForm);
        return "redirect:/";
    }

    @GetMapping("/")
    public String mainScreen(Principal principal) {
        return "welcome";
    }

}
