package pl.mazur.rental.service;

import org.springframework.stereotype.Service;
import pl.mazur.rental.model.Machine;
import pl.mazur.rental.model.MachineGroup;
import pl.mazur.rental.repostiory.MachineRepository;

import java.util.List;


@Service
public class MachineServiceImpl implements MachineService {


    private MachineRepository machineRepository;
    private MachineGroupService machineGroupService;

    public MachineServiceImpl(MachineRepository machineRepository, MachineGroupService machineGroupService) {
        this.machineRepository = machineRepository;
        this.machineGroupService = machineGroupService;
    }

    @Override
    public List<Machine> findByMachineGroup_IdMachineGroup(Long idGroup) {
        return machineRepository.findByMachineGroup_IdGroup(idGroup);
    }

    @Override
    public MachineGroup findMachineGroupByIdGroup(Long idGroup) {
        return machineGroupService.findById(idGroup);
    }

    @Override
    public void save(Machine machine) {
        machine.setRent(false);
        machineRepository.save(machine);
    }

    @Override
    public void deleteMachineById(Long idMachine) {
        machineRepository.deleteById(idMachine);
    }

    @Override
    public Long findIdGroupByIdMachine(Long idMachine) {
        return machineRepository.getOne(idMachine).getMachineGroup().getIdGroup();
    }

    @Override
    public Machine findMachineById(Long idMachine) {
        return machineRepository.getOne(idMachine);
    }

    @Override
    public void addingQuantities(Long idGroup) {
        if (machineGroupService.findById(idGroup) != null) {
            MachineGroup machineGroup = machineGroupService.findById(idGroup);
            if (machineGroup.getAmountOfMachines() != null) {
                Integer actualAmountsOfMachines = machineGroup.getAmountOfMachines();
                machineGroup.setAmountOfMachines(actualAmountsOfMachines + 1);
            } else {
                machineGroup.setAmountOfMachines(1);
            }
            machineGroupService.save(machineGroup);
        }
    }

    @Override
    public void removalQuantities(Long idGroup) {
        if (null != machineGroupService.findById(idGroup)) {
            MachineGroup machineGroup = machineGroupService.findById(idGroup);
            Integer actualAmountsOfMachines = machineGroup.getAmountOfMachines();
            if (actualAmountsOfMachines != null && actualAmountsOfMachines > 0) {
                machineGroup.setAmountOfMachines(actualAmountsOfMachines - 1);
                machineGroupService.save(machineGroup);
            }
        }
    }
}
