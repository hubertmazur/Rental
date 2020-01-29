package pl.mazur.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mazur.rental.model.User;
import pl.mazur.rental.model.UserEdit;
import pl.mazur.rental.model.UserEditMail;
import pl.mazur.rental.service.UserService;
import pl.mazur.rental.validator.EditValidator;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    private EditValidator editValidator;

    public UserController(UserService userService, EditValidator editValidator) {
        this.userService = userService;
        this.editValidator = editValidator;
    }

    @GetMapping("/all")
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

    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable Long userId) {
        userService.deleteById(userId);
        return "redirect:/users";
    }

    @GetMapping("/reservations")
    public String getUserReservation(Model model) {
        model.addAttribute("reservations", userService.findAllReservationByUserId(userService.getAuthUser().getIdUser()));
        return "reservations_user";
    }

    @GetMapping(value = "/update")
    public String updateUser() {
        return "updateUser";
    }

    @GetMapping(value = "/changeMail")
    public String changeMail(Model model) {
        User user = userService.getAuthUser();
        UserEditMail editModel = new UserEditMail();
        editModel.setActualMail(user.getEmail());
        model.addAttribute("editForm", editModel);
        return "edit-mail-form";
    }

    @PutMapping(value = "/changeMail")
    public String changeMail(@ModelAttribute("editForm") @Valid UserEditMail editModel, BindingResult bindingResult, Model model) {
        User user = userService.getAuthUser();
        editValidator.validatePassword(user.getPassword(), editModel.getActualPassword(), bindingResult);
        if (bindingResult.hasErrors()) {
            editModel.setActualMail(user.getEmail());
            model.addAttribute("editForm", editModel);
            model.addAttribute("errors", true);
            return "edit-mail-form";
        }
        userService.changeMail(editModel, user.getIdUser());
        user.setEmail(editModel.getNewMail());
        return "redirect:/";
    }

    @GetMapping(value = "/changePassword")
    public String changePassword(Model model) {
        model.addAttribute("editForm", new UserEdit());
        return "edit-password-form";

    }

    @PutMapping(value = "/changePassword")
    public String changePassword(@ModelAttribute("editForm") @Valid UserEdit userForm, BindingResult bindingResult, Model model) {
        User user = userService.getAuthUser();
        editValidator.validatePassword(user.getPassword(), userForm.getActualPassword(), bindingResult);
        editValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", true);
            return "edit-password-form";
        }

        Long userId = user.getIdUser();
        userService.changePassword(userForm, userId);

        return "redirect:/logout";
    }

}
