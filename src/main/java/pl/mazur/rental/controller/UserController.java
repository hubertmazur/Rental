package pl.mazur.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mazur.rental.model.User;
import pl.mazur.rental.service.UserService;

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

    @DeleteMapping("/users/deleteUser/{userId}")
    public String deleteUserById(@PathVariable Long userId) {
        userService.deleteById(userId);
        return "redirect:/users";
    }

    @GetMapping("users/{userId}")
    public String getUserReservation(@PathVariable Long userId) {
        return "getUserReservation";
    }


}
