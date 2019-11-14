package pl.mazur.rental.service;

import org.springframework.stereotype.Service;
import pl.mazur.rental.model.MachineGroup;
import pl.mazur.rental.model.Reservation;
import pl.mazur.rental.model.User;
import pl.mazur.rental.repostiory.MachineGroupRepository;
import pl.mazur.rental.repostiory.ReservationRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private MachineGroupRepository machineGroupRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, MachineGroupRepository machineGroupRepository) {
        this.reservationRepository = reservationRepository;
        this.machineGroupRepository = machineGroupRepository;
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);

    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findByMachineGroup(MachineGroup machineGroup) {
        return reservationRepository.findAllByMachineGroupList(machineGroup);
    }

    @Override
    public List<Reservation> findByUser(User user) {
        return reservationRepository.findAllByUser(user);
    }


    @Override
    public Reservation findById(Long id) {
        return reservationRepository.getOne(id);
    }

    @Override
    public boolean checkAvailability(Reservation reservation, Long idGroup) {
        Integer quantityToReservation = reservation.getQuantity();
        Integer amountOfAll = machineGroupRepository.getAmountsOfMachines(idGroup);
        Integer booked = reservationRepository.getCountReservedMachines(idGroup);
        return amountOfAll - booked - quantityToReservation >= 0;
    }

    @Override
    public List<MachineGroup> addGroupsMachine(MachineGroup machineGroup, int quantity) {
        List<MachineGroup> machineGroupList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            machineGroupList.add(machineGroup);
        }
        return machineGroupList;
    }

    @Override
    public boolean checkAvailabilityWhenUpdateReservation(Integer quantityToUpdate, Reservation reservation, Long idGroup) {
        Integer quantityToReservationBeforeUpdate = reservation.getQuantity();
        Integer amountOfAll = machineGroupRepository.getAmountsOfMachines(idGroup);
        Integer booked = reservationRepository.getCountReservedMachines(idGroup);
        return amountOfAll - booked - quantityToUpdate + quantityToReservationBeforeUpdate >= 0;
    }

}