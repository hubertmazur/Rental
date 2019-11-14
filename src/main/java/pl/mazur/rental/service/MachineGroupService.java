package pl.mazur.rental.service;

import pl.mazur.rental.model.Category;
import pl.mazur.rental.model.MachineGroup;

import java.util.List;

public interface MachineGroupService {
    MachineGroup findById(Long id);

    List<MachineGroup> findAll();

    void save(MachineGroup machineGroup);

    void deleteById(Long id);

    Long findIdCategoryByIdGroup(Long idGroup);

    Category findCategoryByIdCategory(Long idCategory);

    List<MachineGroup> findByCategory_IdCategory(Long idCategory);

}
