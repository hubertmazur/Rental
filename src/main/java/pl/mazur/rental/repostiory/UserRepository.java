package pl.mazur.rental.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mazur.rental.model.Reservation;
import pl.mazur.rental.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "SELECT u.reservations FROM User u where u.idUser = :userId")
    List<Reservation> getReservationsByUser(@Param("userId") Long userId);

    User findUserByUsername(String userName);

    Optional<User> findUserByIdUser(Long userId);
}
