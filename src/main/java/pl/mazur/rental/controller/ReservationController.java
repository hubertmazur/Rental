package pl.mazur.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.mazur.rental.Util;
import pl.mazur.rental.model.Reservation;
import pl.mazur.rental.service.MachineGroupService;
import pl.mazur.rental.service.ReservationService;
import pl.mazur.rental.service.UserService;
import pl.mazur.rental.validator.ReservationValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
public class ReservationController {
    private ReservationService reservationService;
    private MachineGroupService machineGroupService;
    private UserService userService;
    private ReservationValidator reservationValidator;
    private Util util;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public ReservationController(ReservationService reservationService, MachineGroupService machineGroupService, UserService userService, ReservationValidator reservationValidator, Util util) {
        this.reservationService = reservationService;
        this.machineGroupService = machineGroupService;
        this.userService = userService;
        this.reservationValidator = reservationValidator;
        this.util = util;
    }

    @GetMapping("reservations/machineGroup/{idMachineGroup}")
    public String findAllReservationsByMachineGroup(Model model, @PathVariable Long idMachineGroup) {
        model.addAttribute("allReservations", reservationService.findByMachineGroup(machineGroupService.findById(idMachineGroup)));
        model.addAttribute("name", machineGroupService.findById(idMachineGroup).getName());
        return "reservations";
    }

    @GetMapping("newReservation/{idMachineGroup}")
    public String addNewReservation(Model model, @PathVariable Long idMachineGroup) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("name", machineGroupService.findById(idMachineGroup).getName());
        return "newReservation";
    }

    @PostMapping("newReservation/{idMachineGroup}")
    public String newReservation(Model model, @ModelAttribute Reservation reservation, @PathVariable Long idMachineGroup,
                                 @ModelAttribute("email") String email, BindingResult bindingResult,
                                 Errors error) throws ParseException {
        reservationValidator.validateAvailability(reservation, idMachineGroup, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("reservation", reservation);
            return "newReservation";
        }
        reservation.setUser(userService.findByEmail(email));
        reservation.setMachineGroupList(reservationService.addGroupsMachine(machineGroupService.findById(idMachineGroup),
                reservation.getQuantity()));
        reservation.setStartRentDate(util.parseDate(format, reservation.getStartRentDateTemp()));
        reservation.setEndRentDate(util.parseDate(format, reservation.getEndRentDateTemp()));
        reservationService.save(reservation);
        return "redirect:/reservations/" + idMachineGroup;
    }

    @GetMapping("update/reservation/{idReservation}")
    public String updateReservationById(Model model, @PathVariable Long idReservation) {
        model.addAttribute("reservationToUpdate", reservationService.findById(idReservation));
        return "updateReservation";
    }

    @PutMapping("update/reservation/{idReservation}")
    public String updateReservation(Model model, @PathVariable Long idReservation, @ModelAttribute Reservation reservation,
                                    @ModelAttribute("quantityToUpdate") Integer quantityToUpdate, BindingResult bindingResult, Errors error) throws ParseException {
        Long idMachineGroup = reservationService.findById(idReservation).getMachineGroupList().get(0).getIdGroup();
        reservationValidator.validateAvailabilityWhenUpdateReservation(quantityToUpdate, reservation, idMachineGroup, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("reservationToUpdate", reservation);
            return "updateReservation";
        }
        reservation.setQuantity(quantityToUpdate);
        reservation.setMachineGroupList(reservationService.addGroupsMachine(machineGroupService.findById(idMachineGroup),
                reservation.getQuantity()));
        reservation.setStartRentDate(util.parseDate(format, reservation.getStartRentDateTemp()));
        reservation.setEndRentDate(util.parseDate(format, reservation.getEndRentDateTemp()));
        reservationService.save(reservation);
        model.addAttribute("reservation", reservationService.findById(idReservation));
        return "reservationInfo";
    }

    @DeleteMapping("delete/reservation/{idReservation}")
    public String deleteReservationById(@PathVariable Long idReservation) {
        reservationService.deleteById(idReservation);
        return "redirect:/categoryList";
    }
}
