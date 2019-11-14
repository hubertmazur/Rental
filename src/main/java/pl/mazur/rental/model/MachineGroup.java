package pl.mazur.rental.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class MachineGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGroup;
    @NotNull
    @Length(max = 5)
    private String groupCode;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Double weight;
    @NotNull
    private BigDecimal costPerDay;
    private Integer amountOfMachines;
    @OneToMany(mappedBy = "machineGroup")
    private Set<Machine> machines;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToMany(mappedBy = "machineGroupList")
    private Set<Reservation> reservations;

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public Integer getAmountOfMachines() {
        return amountOfMachines;
    }

    public void setAmountOfMachines(Integer amountOfMachines) {
        this.amountOfMachines = amountOfMachines;
    }

    public Set<Machine> getMachines() {
        return machines;
    }

    public void setMachines(Set<Machine> machines) {
        this.machines = machines;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "MachineGroup{" +
                "idGroup=" + idGroup +
                ", groupCode='" + groupCode + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", costPerDay=" + costPerDay +
                ", amountOfMachines=" + amountOfMachines +
                ", machines=" +
                ", category="  +
                ", reservations=" +
                '}';
    }
}
