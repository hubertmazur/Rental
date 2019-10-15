package pl.mazur.rental.service;

import org.springframework.stereotype.Service;
import pl.mazur.rental.model.Category;
import pl.mazur.rental.repostiory.CategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void save(Category category) {

        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Optional<Category> findById(Long idCategory) {
        return categoryRepository.findById(idCategory);
    }

    public void deleteById(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }
}
