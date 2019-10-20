package pl.mazur.rental.service;

import pl.mazur.rental.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    void deleteById(Long id);

    List<User> findAll();

    User findById(Long id);

    User findByEmail(String email);
}
