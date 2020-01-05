package pl.mazur.rental.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    @Column(name = "serialNumber", unique = true)
    private String serialNumber;
    @NotNull
    @Min(1990)
    @Max(2020)
    private Integer yearOfProduction;
    @Column(columnDefinition = "boolean default false")
    private Boolean isRent;
    private Date startRentDate;
    private Date endRentDate;

    public Long getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(Long idMachine) {
        this.idMachine = idMachine;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public MachineGroup getMachineGroup() {
        return machineGroup;
    }

    public void setMachineGroup(MachineGroup machineGroup) {
        this.machineGroup = machineGroup;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Boolean getRent() {
        return isRent;
    }

    public void setRent(Boolean rent) {
        isRent = rent;
    }

    public Date getStartRentDate() {
        return startRentDate;
    }

    public void setStartRentDate(Date startRentDate) {
        this.startRentDate = startRentDate;
    }

    public Date getEndRentDate() {
        return endRentDate;
    }

    public void setEndRentDate(Date endRentDate) {
        this.endRentDate = endRentDate;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "idMachine=" + idMachine +
                ", manufacturer='" + manufacturer + '\'' +
                ", machineGroup=" + machineGroup.getName() +
                ", serialNumber='" + serialNumber + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", isRent=" + isRent +
                ", startRentDate=" + startRentDate +
                ", endRentDate=" + endRentDate +
                '}';
    }
}