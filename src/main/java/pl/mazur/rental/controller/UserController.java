package pl.mazur.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.mazur.rental.model.User;
import pl.mazur.rental.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("usersList", userService.findAll());
        return "users";
    }

    @GetMapping("addNewUser")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());
        return "newUser";
    }

    @PostMapping("addNewUser")
    public String addNewUser(User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("updateUser/{userId}")
    public String updateUser(@PathVariable Long userId, Model model) {
        model.addAttribute("userToUpdate", userService.findById(userId));
        return "updateUser";
    }

    @PutMapping("updateUser/{userId}")
    public String update_user(@PathVariable Long userId, User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete/user/{userId}")
    public String deleteUserById(@PathVariable Long userId) {
        userService.deleteById(userId);
        return "redirect:/users";
    }

    @GetMapping("reservations/user/{userId}")
    public String getUserReservation(@PathVariable Long userId, Model model) {
        model.addAttribute("reservations", userService.findAllReservationByUserId(userId));
        model.addAttribute("userId", userId);
        return "reservations_user";
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
    public String registration(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, HttpServletRequest request, Errors error) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        return "redirect:/";

    }


}
