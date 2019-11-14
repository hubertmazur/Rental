package pl.mazur.rental.validator;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.mazur.rental.model.Reservation;
import pl.mazur.rental.service.ReservationService;

@Component
public class ReservationValidator implements Validator {

    private ReservationService reservationService;

    public ReservationValidator(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {

    }

    public void validateAvailability(@Nullable Object o, Long idMachineGroup, Errors errors) {
        Reservation reservation = (Reservation) o;
        if (!reservationService.checkAvailability(reservation, idMachineGroup)) {
            errors.reject("quantity", "noAvailability");

        }

    }

    public void validateAvailabilityWhenUpdateReservation(Integer quantityToUpdate, @Nullable Object o, Long idMachineGroup, Errors errors) {
        Reservation reservation = (Reservation) o;
        if (!reservationService.checkAvailabilityWhenUpdateReservation(quantityToUpdate, reservation, idMachineGroup)) {
            errors.reject("quantity", "noAvailability");

        }
    }

}
