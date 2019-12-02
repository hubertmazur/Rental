package pl.mazur.rental.service;

import org.springframework.stereotype.Service;
import pl.mazur.rental.model.Category;
import pl.mazur.rental.repostiory.CategoryRepository;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public void save(Category category) {
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findByCategoryName(String name) {
        return categoryRepository.findByName(name);
    }

}