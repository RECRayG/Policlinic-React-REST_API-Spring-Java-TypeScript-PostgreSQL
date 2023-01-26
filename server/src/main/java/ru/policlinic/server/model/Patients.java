package ru.policlinic.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Data
@ToString
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients", schema = "public", catalog = "policlinic")
public class Patients implements Serializable {
//    @Override
//    public String toString() {
//        return "Patients{" +
//                "lastName='" + lastName + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", middleName='" + middleName + '\'' +
//                '}';
//    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_patient")
    private int idPatient;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "middle_name")
    private String middleName;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "building")
    private String building;
    @Basic
    @Column(name = "apartment")
    private String apartment;
    @OneToMany(mappedBy = "patientsByIdPatient", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<AnalysesResults> analysesResultsByIdPatient;
    @OneToMany(mappedBy = "patientsByIdPatient", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<PatRecIdentity> patRecIdentitiesByIdPatient;

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Patients patients = (Patients) o;
//
//        if (idPatient != patients.idPatient) return false;
//        if (lastName != null ? !lastName.equals(patients.lastName) : patients.lastName != null) return false;
//        if (firstName != null ? !firstName.equals(patients.firstName) : patients.firstName != null) return false;
//        if (middleName != null ? !middleName.equals(patients.middleName) : patients.middleName != null) return false;
//        if (city != null ? !city.equals(patients.city) : patients.city != null) return false;
//        if (street != null ? !street.equals(patients.street) : patients.street != null) return false;
//        if (building != null ? !building.equals(patients.building) : patients.building != null) return false;
//        if (apartment != null ? !apartment.equals(patients.apartment) : patients.apartment != null) return false;
//
//        return true;
//    }
//
//    public boolean equalsWithoutId(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Patients patients = (Patients) o;
//
//        if (lastName != null ? !lastName.equals(patients.lastName) : patients.lastName != null) return false;
//        if (firstName != null ? !firstName.equals(patients.firstName) : patients.firstName != null) return false;
//        if (middleName != null ? !middleName.equals(patients.middleName) : patients.middleName != null) return false;
//        if (city != null ? !city.equals(patients.city) : patients.city != null) return false;
//        if (street != null ? !street.equals(patients.street) : patients.street != null) return false;
//        if (building != null ? !building.equals(patients.building) : patients.building != null) return false;
//        if (apartment != null ? !apartment.equals(patients.apartment) : patients.apartment != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idPatient;
//        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
//        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
//        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
//        result = 31 * result + (city != null ? city.hashCode() : 0);
//        result = 31 * result + (street != null ? street.hashCode() : 0);
//        result = 31 * result + (building != null ? building.hashCode() : 0);
//        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
//        return result;
//    }

    public Collection<AnalysesResults> getAnalysesResultsByIdPatient() {
        return analysesResultsByIdPatient;
    }

    public void setAnalysesResultsByIdPatient(Collection<AnalysesResults> analysesResultsByIdPatient) {
        this.analysesResultsByIdPatient = analysesResultsByIdPatient;
    }

//    public Collection<PatRecIdentity> getPatRecIdentitiesByIdPatient() {
//        return patRecIdentitiesByIdPatient;
//    }
//
//    public void setPatRecIdentitiesByIdPatient(Collection<PatRecIdentity> patRecIdentitiesByIdPatient) {
//        this.patRecIdentitiesByIdPatient = patRecIdentitiesByIdPatient;
//    }
}
