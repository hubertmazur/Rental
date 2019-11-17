package pl.mazur.rental.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCategory;
    @NotNull
    @NotBlank()
    @Column(name = "name", unique = true)
    private String name;
    @OneToMany(mappedBy = "category")
    private Set<MachineGroup> machineGroupList;

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MachineGroup> getMachineGroupList() {
        return machineGroupList;
    }

    public void setMachineGroupList(Set<MachineGroup> machineGroupList) {
        this.machineGroupList = machineGroupList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", machineGroupList=" +
                '}';
    }
}