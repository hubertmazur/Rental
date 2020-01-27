package pl.mazur.rental.model;

import org.hibernate.validator.constraints.Length;

public class UserEdit {
    @Length(min = 1, message = "Pole nie może być puste")
    private String actualPassword;
    @Length(min = 1, message = "Pole nie może być puste")
    private String newPassword;
    @Length(min = 1, message = "Pole nie może być puste")
    private String passwordConfirm;

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
