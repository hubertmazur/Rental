package pl.mazur.rental.model;

import javax.validation.constraints.Email;

public class UserEditMail {

    private String actualPassword;
    @Email
    private String actualMail;
    @Email
    private String newMail;

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getActualMail() {
        return actualMail;
    }

    public void setActualMail(String actualMail) {
        this.actualMail = actualMail;
    }

    public String getNewMail() {
        return newMail;
    }

    public void setNewMail(String newMail) {
        this.newMail = newMail;
    }
}
