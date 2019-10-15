package pl.mazur.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mazur.rental.model.Availability;
import pl.mazur.rental.model.MachineGroup;
import pl.mazur.rental.service.MachineGroupService;

@Controller
public class MachineGroupController {

    private MachineGroupService machineGroupService;

    public MachineGroupController(MachineGroupService machineGroupRepository) {
        this.machineGroupService = machineGroupRepository;
    }


    @GetMapping("category/{idCategory}")
    public String getMachineGroup(Model model, @PathVariable Long idCategory) {
        model.addAttribute("groupList", machineGroupService.findByCategory_IdCategory(idCategory));
        model.addAttribute("category", machineGroupService.findCategoryByIfCategory(idCategory));
        return "machineGroup";

    }

    @GetMapping("group/{idGroup}")
    public String getMachines(Model model, @PathVariable Long idGroup) {
        model.addAttribute("machine", machineGroupService.findByIdGroup(idGroup));
        return "machineGroupInfo";
    }

    @GetMapping("availability/group/{idGroup}")
    public String checkAvailability(@PathVariable Long idGroup, Model model) {
        model.addAttribute("machine", machineGroupService.findByIdGroup(idGroup));
        model.addAttribute("availability", new Availability());
        return "availabilityForm";
    }

    @GetMapping("addNewGroupMachine/category/{idCategory}")
    public String addNewMachineGroup(Model model, @PathVariable Long idCategory) {
        model.addAttribute("idCategory", idCategory);
        model.addAttribute("newGroupMachine", new MachineGroup());
        return "newGroupMachine";
    }

    @PostMapping("addNewGroupMachine/category/{idCategory}")
    public String newGroupMachine(MachineGroup machineGroup, @PathVariable Long idCategory) {
        machineGroup.setCategory(machineGroupService.findCategoryByIfCategory(idCategory));
        machineGroupService.save(machineGroup);
        return "redirect:/category/" + idCategory;
    }

    @GetMapping("updateGroupMachine/{idGroup}")
    public String updateGroupMachine(@PathVariable Long idGroup, Model model) {
        model.addAttribute("updateGroupMachine", machineGroupService.findByIdGroup(idGroup));
        return "updateGroupMachine";
    }

    @PutMapping("updateGroupMachine/{idGroup}")
    public String updateGroup(MachineGroup machineGroup, @PathVariable Long idGroup) {
        Long id = machineGroupService.findIdCategoryByIdGroup(idGroup);
        machineGroup.setCategory(machineGroupService.findCategoryByIfCategory(id));
        machineGroupService.save(machineGroup);
        return "redirect:/category/" + id;
    }


    @DeleteMapping("deleteGroup/{idGroup}")
    public String deleteGroupMachineById(@PathVariable Long idGroup) {
        Long id = machineGroupService.findIdCategoryByIdGroup(idGroup);
        machineGroupService.deleteById(idGroup);
        return "redirect:/category/" + id;
    }


}