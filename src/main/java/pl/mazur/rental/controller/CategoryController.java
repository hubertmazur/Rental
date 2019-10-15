package pl.mazur.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mazur.rental.model.Category;
import pl.mazur.rental.service.CategoryService;

@Controller
public class CategoryController {


    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categoryList")
    public String getCategoryList(Model model) {
        model.addAttribute("categoryList", categoryService.findAll());
        return "category";
    }

    @GetMapping("/addNewCategory")
    public String addNewCategory(Model model) {
        model.addAttribute("newCategory", new Category());
        return "newCategory";
    }

    @PostMapping("/addNewCategory")
    public String addCategory(Model model, Category category) {
        categoryService.save(category);
        return "redirect:/categoryList";


    }

    @GetMapping("/updateCategory/{idCategory}")
    public String updateCategory(@PathVariable Long idCategory, Model model) {
        model.addAttribute("updateCategory", categoryService.findById(idCategory));
        return "updateCategory";
    }

    @PutMapping("/updateCategory/{idCategory}")
    public String updateNameCategory(@PathVariable Long idCategory, Category category) {
        categoryService.save(category);
        return "redirect:/categoryList";
    }

    @DeleteMapping("/deleteCategory/{idCategory}")
    public String deleteCategory(@PathVariable Long idCategory) {
        categoryService.deleteById(idCategory);
        return "redirect:/categoryList";
    }
}