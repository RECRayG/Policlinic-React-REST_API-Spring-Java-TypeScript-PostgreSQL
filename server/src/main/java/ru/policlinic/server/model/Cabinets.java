package ru.policlinic.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cabinets", schema = "public", catalog = "policlinic")
public class Cabinets implements Serializable {
    @Override
    public String toString() {
        return cabinetNumber;
    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cabinet")
    private int idCabinet;
    @Basic
    @Column(name = "cabinet_number")
    private String cabinetNumber;
    @OneToMany(mappedBy = "cabinetsByIdCabinet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Doctors> doctorsByIdCabinet;

    public int getIdCabinet() {
        return idCabinet;
    }

    public void setIdCabinet(int idCabinet) {
        this.idCabinet = idCabinet;
    }

    public String getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(String cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Cabinets cabinets = (Cabinets) o;
//
//        if (idCabinet != cabinets.idCabinet) return false;
//        if (cabinetNumber != null ? !cabinetNumber.equals(cabinets.cabinetNumber) : cabinets.cabinetNumber != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idCabinet;
//        result = 31 * result + (cabinetNumber != null ? cabinetNumber.hashCode() : 0);
//        return result;
//    }

    public Collection<Doctors> getDoctorsByIdCabinet() {
        return doctorsByIdCabinet;
    }

    public void setDoctorsByIdCabinet(Collection<Doctors> doctorsByIdCabinet) {
        this.doctorsByIdCabinet = doctorsByIdCabinet;
    }
}
