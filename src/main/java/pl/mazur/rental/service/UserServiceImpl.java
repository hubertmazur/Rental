package pl.mazur.rental.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mazur.rental.model.*;
import pl.mazur.rental.repostiory.RoleRepository;
import pl.mazur.rental.repostiory.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(User user) {

        if (roleRepository.findRoleByName("ROLE_USER") == null) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            Role roleOfUser = roleRepository.findRoleByName("ROLE_USER");
            user.setRoles(new HashSet<>(Collections.singletonList(roleOfUser)));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Reservation> findAllReservationByUserId(Long userId) {
        return userRepository.getReservationsByUser(userId);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findUserByUsername(userName);
    }

    @Override
    public User findUserByIdUser(Long userId) {
        return userRepository.findUserByIdUser(userId).get();
    }

    @Override
    public void changePassword(UserEdit userEdit, Long id) {
        User user = userRepository.getOne(id);
        user.setPassword(bCryptPasswordEncoder.encode(userEdit.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void changeMail(UserEditMail userEdit, Long id) {
        User user = userRepository.getOne(id);
        user.setEmail(userEdit.getNewMail());
        userRepository.save(user);
    }

    @Override
    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }
}
