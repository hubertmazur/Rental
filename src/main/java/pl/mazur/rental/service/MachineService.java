package pl.mazur.rental.service;

import org.springframework.stereotype.Service;
import pl.mazur.rental.model.Machine;
import pl.mazur.rental.model.MachineGroup;
import pl.mazur.rental.repostiory.MachineGroupRepository;
import pl.mazur.rental.repostiory.MachineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {


    private MachineGroupRepository machineGroupRepository;
    private MachineRepository machineRepository;

    public MachineService(MachineGroupRepository machineGroupRepository, MachineRepository machineRepository) {
        this.machineGroupRepository = machineGroupRepository;
        this.machineRepository = machineRepository;
    }

    public List<Machine> findByMachineGroup_IdMachineGroup(Long idGroup) {
        return machineRepository.findByMachineGroup_IdGroup(idGroup);
    }

    public MachineGroup findMachineGroupByIdGroup(Long idGroup) {
        return machineGroupRepository.findById(idGroup).get();
    }

    public void save(Machine machine) {
        machineRepository.save(machine);
    }

    public void deleteMachineById(Long idMachine) {
        machineRepository.deleteById(idMachine);
    }

    public Long findIdGroupByIdMachine(Long idMachine) {
        return machineRepository.findById(idMachine).get().getMachineGroup().getIdGroup();
    }

    public Optional<Machine> findMachineById(Long idMachine) {
        return machineRepository.findById(idMachine);
    }

    public void addingQuantities(Long idGroup) {
        if (machineGroupRepository.findById(idGroup).isPresent()) {
            MachineGroup machineGroup = machineGroupRepository.findById(idGroup).get();
            if (machineGroup.getAmountOfMachines() != null) {
                Integer actualAmountsOfMachines = machineGroup.getAmountOfMachines();
                machineGroup.setAmountOfMachines(actualAmountsOfMachines + 1);
            } else {
                machineGroup.setAmountOfMachines(1);
            }
            machineGroupRepository.save(machineGroup);
        }
    }

    public void removalQuantities(Long idGroup) {
        if (machineGroupRepository.findById(idGroup).isPresent()) {
            MachineGroup machineGroup = machineGroupRepository.findById(idGroup).get();
            Integer actualAmountsOfMachines = machineGroup.getAmountOfMachines();
            if (actualAmountsOfMachines != null && actualAmountsOfMachines > 0) {
                machineGroup.setAmountOfMachines(actualAmountsOfMachines - 1);
                machineGroupRepository.save(machineGroup);
            }
        }
    }
}
