package pl.mazur.rental.service;

import pl.mazur.rental.model.Machine;
import pl.mazur.rental.model.MachineGroup;

import java.util.List;

public interface MachineService {
    List<Machine> findByMachineGroup_IdMachineGroup(Long idGroup);

    MachineGroup findMachineGroupByIdGroup(Long idGroup);

    void save(Machine machine);

    void deleteMachineById(Long idMachine);

    Long findIdGroupByIdMachine(Long idMachine);

    Machine findMachineById(Long idMachine);

    void addingQuantities(Long idGroup);

    void removalQuantities(Long idGroup);
}
