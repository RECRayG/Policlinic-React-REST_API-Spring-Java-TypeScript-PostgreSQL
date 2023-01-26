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
@Table(name = "specializations", schema = "public", catalog = "policlinic")
public class Specializations implements Serializable {
    @Override
    public String toString() {
        return specializationName;
    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_specialization")
    private int idSpecialization;
    @Basic
    @Column(name = "specialization_name")
    private String specializationName;
    @OneToMany(mappedBy = "specializationsByIdSpecialization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Doctors> doctorsByIdSpecialization;

    public int getIdSpecialization() {
        return idSpecialization;
    }

    public void setIdSpecialization(int idSpecialization) {
        this.idSpecialization = idSpecialization;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Specializations that = (Specializations) o;
//
//        if (idSpecialization != that.idSpecialization) return false;
//        if (specializationName != null ? !specializationName.equals(that.specializationName) : that.specializationName != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idSpecialization;
//        result = 31 * result + (specializationName != null ? specializationName.hashCode() : 0);
//        return result;
//    }

    public Collection<Doctors> getDoctorsByIdSpecialization() {
        return doctorsByIdSpecialization;
    }

    public void setDoctorsByIdSpecialization(Collection<Doctors> doctorsByIdSpecialization) {
        this.doctorsByIdSpecialization = doctorsByIdSpecialization;
    }
}
