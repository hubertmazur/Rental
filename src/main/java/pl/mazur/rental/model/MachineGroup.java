package pl.mazur.rental.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
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
    @OneToMany(mappedBy="machineGroup")
    private Set<Machine> machines;
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @Override
    public String toString() {
        return "MachineGroup{" +
                "idGroup=" + idGroup +
                ", groupCode='" + groupCode + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", costPerDay=" + costPerDay +
                ", amountOfMachines=" + amountOfMachines +
                ", machines=" + machines.isEmpty() +
                ", category=" + category.getName() +
                '}';
    }
}
