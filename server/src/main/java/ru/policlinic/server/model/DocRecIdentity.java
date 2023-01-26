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
@Table(name = "doc_rec_identity", schema = "public", catalog = "policlinic")
public class DocRecIdentity implements Serializable {
//    @Override
//    public String toString() {
//        return "DocRecIdentity{" +
//                "doctorsByIdDoctor=" + doctorsByIdDoctor.toString() +
//                '}';
//    }

    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})//CascadeType.ALL)
    @JoinColumn(name = "id_doctor", referencedColumnName = "id_doctor", nullable = false)
    private Doctors doctorsByIdDoctor;
    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_reception", referencedColumnName = "id_reception", nullable = false)
    private Receptions receptionsByIdReception;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doc_rec_identity")
    private int idDocRecIdentity;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        DocRecIdentity that = (DocRecIdentity) o;
//
//        if (idDocRecIdentity != that.idDocRecIdentity) return false;
//        if (doctorsByIdDoctor != that.doctorsByIdDoctor) return false;
//        if (receptionsByIdReception != that.receptionsByIdReception) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = doctorsByIdDoctor.getIdDoctor();
//        result = 31 * result + receptionsByIdReception.getIdReception();
//        result = 31 * result + idDocRecIdentity;
//        return result;
//    }

    public Doctors getDoctorsByIdDoctor() {
        return doctorsByIdDoctor;
    }

    public void setDoctorsByIdDoctor(Doctors doctorsByIdDoctor) {
        this.doctorsByIdDoctor = doctorsByIdDoctor;
    }

    public Receptions getReceptionsByIdReception() {
        return receptionsByIdReception;
    }

    public void setReceptionsByIdReception(Receptions receptionsByIdReception) {
        this.receptionsByIdReception = receptionsByIdReception;
    }

    public void setId(int idDocRecIdentity) {
        this.idDocRecIdentity = idDocRecIdentity;
    }

    public int getId() {
        return idDocRecIdentity;
    }
}
