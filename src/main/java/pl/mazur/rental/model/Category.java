package pl.mazur.rental.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCategory;
    @NotNull
    @NotBlank
    @Column(name = "name", unique = true)
    private String name;
    @OneToMany(mappedBy = "category")
    Set<MachineGroup> machineGroupList;

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", machineGroupList=" + machineGroupList.isEmpty() +
                '}';
    }
}