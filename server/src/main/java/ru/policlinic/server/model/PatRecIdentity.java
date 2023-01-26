package ru.policlinic.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pat_rec_identity", schema = "public", catalog = "policlinic")
public class PatRecIdentity implements Serializable {
    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_patient", referencedColumnName = "id_patient", nullable = false)
    private Patients patientsByIdPatient;
    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_reception", referencedColumnName = "id_reception", nullable = false)
    private Receptions receptionsByIdReception;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pat_rec_identity")
    private int idPatRecIdentity;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        PatRecIdentity that = (PatRecIdentity) o;
//
//        if (idPatRecIdentity != that.idPatRecIdentity) return false;
//        if (patientsByIdPatient != that.patientsByIdPatient) return false;
//        if (receptionsByIdReception != that.receptionsByIdReception) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = patientsByIdPatient.getIdPatient();
//        result = 31 * result + receptionsByIdReception.getIdReception();
//        result = 31 * result + idPatRecIdentity;
//        return result;
//    }

    public Patients getPatientsByIdPatient() {
        return patientsByIdPatient;
    }

    public void setPatientsByIdPatient(Patients patientsByIdPatient) {
        this.patientsByIdPatient = patientsByIdPatient;
    }

    public Receptions getReceptionsByIdReception() {
        return receptionsByIdReception;
    }

    public void setReceptionsByIdReception(Receptions receptionsByIdReception) {
        this.receptionsByIdReception = receptionsByIdReception;
    }

    public void setId(int idPatRecIdentity) {
        this.idPatRecIdentity = idPatRecIdentity;
    }

    public int getId() {
        return idPatRecIdentity;
    }
}
