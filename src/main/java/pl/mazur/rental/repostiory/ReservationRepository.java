package pl.mazur.rental.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mazur.rental.model.MachineGroup;
import pl.mazur.rental.model.Reservation;
import pl.mazur.rental.model.User;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByMachineGroupList(MachineGroup machineGroup);

    List<Reservation> findAllByUser(User user);

    @Query(value = "select count(machine_group_id) from reservation_machine_group rm  \n" +
            "inner join reservation r ON r.id_reservation = rm.reservation_id and rm.machine_group_id = :machineGroupId  \n" +
            "and r.end_rent_date >= now() ", nativeQuery = true)
    Integer getCountReservedMachines(@Param("machineGroupId") Long machineGroupId);
}
