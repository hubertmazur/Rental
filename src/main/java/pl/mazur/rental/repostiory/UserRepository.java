package pl.mazur.rental.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mazur.rental.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
