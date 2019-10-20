package pl.mazur.rental.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMachine;
    @NotNull
    @NotBlank
    private String manufacturer;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "machine_group_id", nullable = false)
    private MachineGroup machineGroup;
    @NotNull
    private String serialNumber;
    @NotNull
    @Min(1990)
    @Max(2019)
    private Integer yearOfProduction;
    @Column(columnDefinition = "boolean default false")
    private Boolean isRent;
    private Date startRentDate;
    private Date endRentDate;

    @Override
    public String toString() {
        return "Machine{" +
                "idMachine=" + idMachine +
                ", machineGroup=" + machineGroup.getName() +
                ", serialNumber=" + serialNumber +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}