package pl.mazur.rental.service;

import org.springframework.stereotype.Service;
import pl.mazur.rental.model.Category;
import pl.mazur.rental.model.MachineGroup;
import pl.mazur.rental.repostiory.CategoryRepository;
import pl.mazur.rental.repostiory.MachineGroupRepository;
import java.util.List;

@Service
public class MachineGroupService {

    private CategoryRepository categoryRepository;
    private MachineGroupRepository machineGroupRepository;

    public MachineGroupService(CategoryRepository categoryRepository, MachineGroupRepository machineGroupRepository) {
        this.categoryRepository = categoryRepository;
        this.machineGroupRepository = machineGroupRepository;
    }

    public Long findIdCategoryByIdGroup(Long idGroup) {
        return machineGroupRepository.getOne(idGroup).getCategory().getIdCategory();
    }

    public Category findCategoryByIfCategory(Long idCategory) {
        return categoryRepository.findById(idCategory).get();
    }

    public void save(MachineGroup machineGroup) {
        machineGroupRepository.save(machineGroup);
    }

    public void deleteById(Long idGroup) {
        machineGroupRepository.deleteById(idGroup);
    }

    public List<MachineGroup> findByCategory_IdCategory(Long idCategory) {
        return machineGroupRepository.findByCategory_IdCategory(idCategory);
    }

    public MachineGroup findByIdGroup(Long idGroup) {
        return machineGroupRepository.findById(idGroup).get();
    }
}
