package pl.mazur.rental.service;

import org.springframework.stereotype.Service;
import pl.mazur.rental.model.Category;
import pl.mazur.rental.model.MachineGroup;
import pl.mazur.rental.repostiory.CategoryRepository;
import pl.mazur.rental.repostiory.MachineGroupRepository;
import pl.mazur.rental.repostiory.MachineRepository;

import java.util.List;

@Service
public class MachineGroupServiceImpl implements MachineGroupService {

    private CategoryRepository categoryRepository;
    private MachineGroupRepository machineGroupRepository;
    private MachineRepository machineRepository;

    public MachineGroupServiceImpl(CategoryRepository categoryRepository, MachineGroupRepository machineGroupRepository, MachineRepository machineRepository) {
        this.categoryRepository = categoryRepository;
        this.machineGroupRepository = machineGroupRepository;
        this.machineRepository = machineRepository;
    }

    @Override
    public MachineGroup findById(Long id) {
        return machineGroupRepository.getOne(id);
    }

    @Override
    public List<MachineGroup> findAll() {
        return machineGroupRepository.findAll();
    }

    @Override
    public void save(MachineGroup machineGroup) {
        machineGroupRepository.save(machineGroup);
    }

    @Override
    public void deleteById(Long id) {
        machineGroupRepository.deleteById(id);
    }

    @Override
    public Long findIdCategoryByIdGroup(Long idGroup) {
        return machineGroupRepository.getOne(idGroup).getCategory().getIdCategory();
    }

    @Override
    public Category findCategoryByIdCategory(Long idCategory) {
        return categoryRepository.getOne(idCategory);
    }

    @Override
    public List<MachineGroup> findByCategory_IdCategory(Long idCategory) {
        return machineGroupRepository.findByCategory_IdCategory(idCategory);
    }

}
