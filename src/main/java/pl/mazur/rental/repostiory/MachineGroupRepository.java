package pl.mazur.rental.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mazur.rental.model.MachineGroup;

import java.util.List;

public interface MachineGroupRepository extends JpaRepository<MachineGroup, Long> {
    List<MachineGroup> findByCategory_IdCategory(Long idCategory);
}
