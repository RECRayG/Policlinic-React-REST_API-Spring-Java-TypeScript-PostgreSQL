package ru.policlinic.server.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rec_med_identity", schema = "public", catalog = "policlinic")
public class RecMedIdentity {
    @ManyToOne
    @JoinColumn(name = "id_reception", referencedColumnName = "id_reception", nullable = false)
    private Receptions receptionsByIdReception;
    @ManyToOne
    @JoinColumn(name = "id_medication", referencedColumnName = "id_medication", nullable = false)
    private Medications medicationsByIdMedication;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rec_med_identity")
    private int idRecMedIdentity;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        RecMedIdentity that = (RecMedIdentity) o;
//
//        if (idRecMedIdentity != that.idRecMedIdentity) return false;
//        if (receptionsByIdReception != that.receptionsByIdReception) return false;
//        if (medicationsByIdMedication != that.medicationsByIdMedication) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idRecMedIdentity;
//        result = 31 * result + medicationsByIdMedication.getIdMedication();
//        result = 31 * result + receptionsByIdReception.getIdReception();
//        return result;
//    }

    public Receptions getReceptionsByIdReception() {
        return receptionsByIdReception;
    }

    public void setReceptionsByIdReception(Receptions receptionsByIdReception) {
        this.receptionsByIdReception = receptionsByIdReception;
    }

    public Medications getMedicationsByIdMedication() {
        return medicationsByIdMedication;
    }

    public void setMedicationsByIdMedication(Medications medicationsByIdMedication) {
        this.medicationsByIdMedication = medicationsByIdMedication;
    }

    public void setId(int idRecMedIdentity) {
        this.idRecMedIdentity = idRecMedIdentity;
    }

    public int getId() {
        return idRecMedIdentity;
    }
}
