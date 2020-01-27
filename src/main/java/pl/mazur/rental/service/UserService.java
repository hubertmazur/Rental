package pl.mazur.rental.service;

import pl.mazur.rental.model.Reservation;
import pl.mazur.rental.model.User;
import pl.mazur.rental.model.UserEdit;
import pl.mazur.rental.model.UserEditMail;

import java.util.List;

public interface UserService {
    void save(User user);

    void deleteById(Long id);

    List<User> findAll();

    User findById(Long id);

    User findByEmail(String email);

    List<Reservation> findAllReservationByUserId(Long userId);

    User findByUsername(String userName);

    User findUserByIdUser(Long userId);

     void changePassword(UserEdit userEdit, Long id);

    void changeMail(UserEditMail userEdit, Long id);

    User getAuthUser();

    void update(User user);

}
