package pl.mazur.rental.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mazur.rental.model.MachineGroup;

import java.util.List;

@Repository
public interface MachineGroupRepository extends JpaRepository<MachineGroup, Long> {
    List<MachineGroup> findByCategory_IdCategory(Long idCategory);

    @Query("select mg.amountOfMachines from MachineGroup mg where mg.idGroup=:idMachineGroup")
    Integer getAmountsOfMachines (Long idMachineGroup);

}
