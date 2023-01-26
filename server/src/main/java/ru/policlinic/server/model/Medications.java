package ru.policlinic.server.model;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "medications", schema = "public", catalog = "policlinic")
public class Medications {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_medication")
    private int idMedication;
    @Basic
    @Column(name = "medication_name")
    private String medicationName;
    @OneToMany(mappedBy = "medicationsByIdMedication", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = false)
    private Collection<RecMedIdentity> recMedIdentitiesByIdMedication;

    public int getIdMedication() {
        return idMedication;
    }

    public void setIdMedication(int idMedication) {
        this.idMedication = idMedication;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Medications that = (Medications) o;
//
//        if (idMedication != that.idMedication) return false;
//        if (medicationName != null ? !medicationName.equals(that.medicationName) : that.medicationName != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idMedication;
//        result = 31 * result + (medicationName != null ? medicationName.hashCode() : 0);
//        return result;
//    }

    public Collection<RecMedIdentity> getRecMedIdentitiesByIdMedication() {
        return recMedIdentitiesByIdMedication;
    }

    public void setRecMedIdentitiesByIdMedication(Collection<RecMedIdentity> recMedIdentitiesByIdMedication) {
        this.recMedIdentitiesByIdMedication = recMedIdentitiesByIdMedication;
    }
}
