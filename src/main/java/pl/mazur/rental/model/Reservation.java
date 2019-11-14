package pl.mazur.rental.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReservation;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Reservation_MachineGroup",
            joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "machineGroup_id")}
    )
    private List<MachineGroup> machineGroupList;
    @Transient
    private String startRentDateTemp;
    @Transient
    private String endRentDateTemp;
    @NotNull
    @Column(name = "start_rent_date")
    @Type(type = "date")
    @Temporal(TemporalType.DATE)
    private Date startRentDate;
    @NotNull
    @Column(name = "end_rent_date")
    @Type(type = "date")
    @Temporal(TemporalType.DATE)
    private Date endRentDate;
    @NotNull
    private Integer quantity;

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MachineGroup> getMachineGroupList() {
        return machineGroupList;
    }

    public void setMachineGroupList(List<MachineGroup> machineGroupList) {
        this.machineGroupList = machineGroupList;
    }

    public String getStartRentDateTemp() {
        return startRentDateTemp;
    }

    public void setStartRentDateTemp(String startRentDateTemp) {
        this.startRentDateTemp = startRentDateTemp;
    }

    public String getEndRentDateTemp() {
        return endRentDateTemp;
    }

    public void setEndRentDateTemp(String endRentDateTemp) {
        this.endRentDateTemp = endRentDateTemp;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", user=" + user +
                ", machineGroupList=" + machineGroupList.size() +
                ", startRentDateTemp='" + startRentDateTemp + '\'' +
                ", endRentDateTemp='" + endRentDateTemp + '\'' +
                ", startRentDate=" + startRentDate +
                ", endRentDate=" + endRentDate +
                ", quantity=" + quantity +
                '}';
    }
}
