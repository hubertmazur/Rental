package pl.mazur.rental.service;

import org.springframework.stereotype.Service;
import pl.mazur.rental.model.Machine;
import pl.mazur.rental.model.MachineGroup;
import pl.mazur.rental.repostiory.MachineGroupRepository;
import pl.mazur.rental.repostiory.MachineRepository;

import java.util.List;


@Service
public class MachineServiceImpl implements MachineService {


    private MachineGroupRepository machineGroupRepository;
    private MachineRepository machineRepository;

    public MachineServiceImpl(MachineGroupRepository machineGroupRepository, MachineRepository machineRepository) {
        this.machineGroupRepository = machineGroupRepository;
        this.machineRepository = machineRepository;
    }


    @Override
    public List<Machine> findByMachineGroup_IdMachineGroup(Long idGroup) {
        return machineRepository.findByMachineGroup_IdGroup(idGroup);
    }

    @Override
    public MachineGroup findMachineGroupByIdGroup(Long idGroup) {
        return machineGroupRepository.getOne(idGroup);
    }

    @Override
    public void save(Machine machine) {
        machine.setIsRent(false);
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

    @Override
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
