package pl.mazur.rental.service;

import pl.mazur.rental.model.MachineGroup;
import pl.mazur.rental.model.Reservation;
import pl.mazur.rental.model.User;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    void save(Reservation reservation);

    void deleteById(Long id);

    List<Reservation> findAll();

    List<Reservation> findByMachineGroup(MachineGroup machineGroup);

    List<Reservation> findByUser(User user);

    Reservation findById(Long id);

    boolean checkAvailability(Reservation reservation, Long idGroup);

    List<MachineGroup> addGroupsMachine(MachineGroup machineGroup, int quantity);


    boolean checkAvailabilityWhenUpdateReservation(Integer quantityToUpdate, Reservation reservation, Long idGroup);

}
