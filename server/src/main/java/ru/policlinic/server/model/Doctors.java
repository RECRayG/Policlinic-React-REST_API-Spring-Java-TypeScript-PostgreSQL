package ru.policlinic.server.model;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.Collection;

@Data
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "doctors", schema = "public", catalog = "policlinic")
public class Doctors {

    @Override
    public String toString() {
        return "Doctors{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", specializationsByIdSpecialization=" + specializationsByIdSpecialization +
                ", cabinetsByIdCabinet=" + cabinetsByIdCabinet +
                ", plotsByIdPlot=" + plotsByIdPlot +
                '}';
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_doctor")
    private int idDoctor;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "middle_name")
    private String middleName;

    @OneToMany(mappedBy = "doctorsByIdDoctor", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<DocRecIdentity> docRecIdentitiesByIdDoctor;

    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_specialization", referencedColumnName = "id_specialization", nullable = false)
    private Specializations specializationsByIdSpecialization;

    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_cabinet", referencedColumnName = "id_cabinet", nullable = false)
    private Cabinets cabinetsByIdCabinet;

    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_plot", referencedColumnName = "id_plot", nullable = false)
    private Plots plotsByIdPlot;

    @OneToMany(mappedBy = "doctorsByIdDoctor", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<DwDocIdentity> dwDocIdentitiesByIdDoctor;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = true)
    private Users usersByIdUser;

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Doctors doctors = (Doctors) o;
//
//        if (idDoctor != doctors.idDoctor) return false;
//        if (specializationsByIdSpecialization != doctors.specializationsByIdSpecialization) return false;
//        if (plotsByIdPlot != doctors.plotsByIdPlot) return false;
//        if (cabinetsByIdCabinet != doctors.cabinetsByIdCabinet) return false;
//        if (lastName != null ? !lastName.equals(doctors.lastName) : doctors.lastName != null) return false;
//        if (firstName != null ? !firstName.equals(doctors.firstName) : doctors.firstName != null) return false;
//        if (middleName != null ? !middleName.equals(doctors.middleName) : doctors.middleName != null) return false;
//        if (usersByIdUser != doctors.usersByIdUser) return false;
//
//        return true;
//    }

//    public boolean equalsWithoutId(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Doctors doctors = (Doctors) o;
//
//        if (specializationsByIdSpecialization.getSpecializationName() != doctors.specializationsByIdSpecialization.getSpecializationName()) return false;
//        if (plotsByIdPlot.getPlotNumber() != doctors.plotsByIdPlot.getPlotNumber()) return false;
//        if (cabinetsByIdCabinet.getCabinetNumber() != doctors.cabinetsByIdCabinet.getCabinetNumber()) return false;
//        if (lastName != null ? !lastName.equals(doctors.lastName) : doctors.lastName != null) return false;
//        if (firstName != null ? !firstName.equals(doctors.firstName) : doctors.firstName != null) return false;
//        if (middleName != null ? !middleName.equals(doctors.middleName) : doctors.middleName != null) return false;
//
//        return true;
//    }

//    @Override
//    public int hashCode() {
//        int result = idDoctor;
//        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
//        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
//        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
//        result = 31 * result + specializationsByIdSpecialization.getIdSpecialization();
//        result = 31 * result + plotsByIdPlot.getIdPlot();
//        result = 31 * result + cabinetsByIdCabinet.getIdCabinet();
//        return result;
//    }

    public Specializations getSpecializationsByIdSpecialization() {
        return specializationsByIdSpecialization;
    }

    public void setSpecializationsByIdSpecialization(Specializations specializationsByIdSpecialization) {
        this.specializationsByIdSpecialization = specializationsByIdSpecialization;
    }

    public Cabinets getCabinetsByIdCabinet() {
        return cabinetsByIdCabinet;
    }

    public void setCabinetsByIdCabinet(Cabinets cabinetsByIdCabinet) {
        this.cabinetsByIdCabinet = cabinetsByIdCabinet;
    }

    public Plots getPlotsByIdPlot() {
        return plotsByIdPlot;
    }

    public void setPlotsByIdPlot(Plots plotsByIdPlot) {
        this.plotsByIdPlot = plotsByIdPlot;
    }
}

