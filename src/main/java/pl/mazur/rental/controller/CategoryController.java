package pl.mazur.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mazur.rental.model.Category;
import pl.mazur.rental.service.CategoryService;
import pl.mazur.rental.validator.CategoryValidator;

import javax.validation.Valid;


@Controller
public class CategoryController {


    private CategoryService categoryService;
    private CategoryValidator categoryValidator;

    public CategoryController(CategoryService categoryService, CategoryValidator categoryValidator) {
        this.categoryService = categoryService;
        this.categoryValidator = categoryValidator;
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
    public String addCategory(Model model, Category category, BindingResult bindingResult) {
        categoryValidator.validate(category, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/addNewCategory";
        }
        categoryService.save(category);
        return "redirect:/categoryList";


    }

    @GetMapping("/updateCategory/{idCategory}")
    public String updateCategory(@PathVariable Long idCategory, Model model) {
        model.addAttribute("category", categoryService.findById(idCategory));
        return "updateCategory";
    }

    @PutMapping("/updateCategory")
    public String updateNameCategory(@Valid Category category, BindingResult bindingResult) {
        categoryValidator.validate(category, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updateCategory";
        }
        categoryService.save(category);
        return "redirect:/categoryList";
    }

    @DeleteMapping("/deleteCategory/{idCategory}")
    public String deleteCategory(@PathVariable Long idCategory) {
        categoryService.deleteById(idCategory);
        return "redirect:/categoryList";
    }
}