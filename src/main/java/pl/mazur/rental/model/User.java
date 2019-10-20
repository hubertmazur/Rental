package pl.mazur.rental.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;
    @NotBlank
    @NotNull
    private String firstName;
    @NotBlank
    @NotNull
    private String lastName;

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pesel='" + pesel + '\'' +
                ", sex='" + sex + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @NotBlank
    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;
    @NotBlank
    @NotNull
    private String telephone;
    @NotBlank
    @NotNull
    private String pesel;
    @NotBlank
    @NotNull
    private String sex;
    @NotBlank
    @NotNull
    private String dateOfBirth;
    @NotBlank
    @NotNull
    private String address;
}
