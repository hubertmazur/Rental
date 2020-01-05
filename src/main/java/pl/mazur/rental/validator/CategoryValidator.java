package pl.mazur.rental.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.mazur.rental.model.Category;
import pl.mazur.rental.service.CategoryService;

@Component
public class CategoryValidator implements Validator {

    private CategoryService categoryService;


    public CategoryValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Category.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Category category = (Category) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");

        if (category.getName().length() < 3 || category.getName().length() > 40) {
            errors.rejectValue("name", "categoryName.length");
        }
        if (categoryService.findByCategoryName(category.getName()) != null) {
            errors.rejectValue("name", "categoryName.duplicate");
        }
        if (category.getName() == null) {
            errors.rejectValue("name", "categoryName.notNull");
        }
        char[] chars = category.getName().toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                errors.rejectValue("name", "categoryName.onlyLetters");
            }
        }


    }
}
