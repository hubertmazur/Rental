package pl.mazur.rental.service;

import pl.mazur.rental.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void deleteById(Long id);
}
