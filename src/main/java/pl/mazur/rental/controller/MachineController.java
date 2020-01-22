package pl.mazur.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mazur.rental.model.Machine;
import pl.mazur.rental.service.MachineService;

@Controller
public class MachineController {

    private MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping("listMachines/group/{idGroup}")
    public String getListMachineByIdGroup(@PathVariable Long idGroup, Model model) {
        model.addAttribute("listMachine", machineService.findByMachineGroup_IdMachineGroup(idGroup));
        model.addAttribute("machineGroup", machineService.findMachineGroupByIdGroup(idGroup));
        return "machine";
    }

    @GetMapping("addNewMachine/groupMachine/{idGroup}")
    public String addNewMachine(@PathVariable Long idGroup, Model model) {
        model.addAttribute("newMachine", new Machine());
        model.addAttribute("machineGroup", machineService.findMachineGroupByIdGroup(idGroup));
        return "newMachine";
    }

    @PostMapping("addNewMachine/groupMachine/{idGroup}")
    public String newMachine(@PathVariable Long idGroup, Model model, Machine machine) {
        machine.setMachineGroup(machineService.findMachineGroupByIdGroup(idGroup));
        machineService.save(machine);
        machineService.addingQuantities(idGroup);
        return "redirect:/listMachines/group/" + idGroup;
    }

    @GetMapping("updateMachine/{idMachine}")
    public String updateMachineById(@PathVariable Long idMachine, Model model) {
        model.addAttribute("machineToUpdate", machineService.findMachineById(idMachine));
        return "updateMachine";
    }

    @PutMapping("updateMachine/{idMachine}")
    public String updateMachine(@PathVariable Long idMachine, Machine machine) {
        Long idGroup = machineService.findIdGroupByIdMachine(idMachine);
        machine.setMachineGroup(machineService.findMachineGroupByIdGroup(idGroup));
        machineService.save(machine);
        return "redirect:/listMachines/group/" + idGroup;
    }


    @DeleteMapping("delete/machine/{idMachine}")
    public String deleteMachine(@PathVariable Long idMachine) {
        Long idGroup = machineService.findIdGroupByIdMachine(idMachine);
        machineService.deleteMachineById(idMachine);
        machineService.removalQuantities(idGroup);
        return "redirect:/listMachines/group/" + idGroup;

    }


}

