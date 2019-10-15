package pl.mazur.rental.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mazur.rental.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
