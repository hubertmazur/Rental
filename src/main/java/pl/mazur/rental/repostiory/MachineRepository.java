package pl.mazur.rental.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mazur.rental.model.Machine;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Long> {
    List<Machine> findByMachineGroup_IdGroup (Long idGroup);

}
