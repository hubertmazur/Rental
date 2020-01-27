package pl.mazur.rental.validator;


import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.mazur.rental.model.UserEdit;


@Component
public class EditValidator implements Validator {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public EditValidator(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        UserEdit userEdit = (UserEdit) o;

        if (!userEdit.getNewPassword().equals(userEdit.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }

    public void validatePassword(String userPass, String actualPass, Errors errors) {

        if (!bCryptPasswordEncoder.matches(actualPass, userPass)) {
            errors.rejectValue("actualPassword", "incorrect.editForm.actualPassword");
        }
    }

}
