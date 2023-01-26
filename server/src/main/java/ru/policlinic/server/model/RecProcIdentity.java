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
@Table(name = "rec_proc_identity", schema = "public", catalog = "policlinic")
public class RecProcIdentity {
    @ManyToOne
    @JoinColumn(name = "id_reception", referencedColumnName = "id_reception", nullable = false)
    private Receptions receptionsByIdReception;
    @ManyToOne
    @JoinColumn(name = "id_procedure", referencedColumnName = "id_procedure", nullable = false)
    private Proceduress proceduressByIdProcedure;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rec_proc_identity")
    private int idRecProcIdentity;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        RecProcIdentity that = (RecProcIdentity) o;
//
//        if (idRecProcIdentity != that.idRecProcIdentity) return false;
//        if (receptionsByIdReception != that.receptionsByIdReception) return false;
//        if (proceduressByIdProcedure != that.proceduressByIdProcedure) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idRecProcIdentity;
//        result = 31 * result + proceduressByIdProcedure.getIdProcedure();
//        result = 31 * result + receptionsByIdReception.getIdReception();
//        return result;
//    }

    public Receptions getReceptionsByIdReception() {
        return receptionsByIdReception;
    }

    public void setReceptionsByIdReception(Receptions receptionsByIdReception) {
        this.receptionsByIdReception = receptionsByIdReception;
    }

    public Proceduress getProceduressByIdProcedure() {
        return proceduressByIdProcedure;
    }

    public void setProceduressByIdProcedure(Proceduress proceduressByIdProcedure) {
        this.proceduressByIdProcedure = proceduressByIdProcedure;
    }

    public void setId(int idRecProcIdentity) {
        this.idRecProcIdentity = idRecProcIdentity;
    }

    public int getId() {
        return idRecProcIdentity;
    }
}
