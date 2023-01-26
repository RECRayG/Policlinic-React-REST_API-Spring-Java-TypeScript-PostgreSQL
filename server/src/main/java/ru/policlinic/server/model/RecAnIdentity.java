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
@Table(name = "rec_an_identity", schema = "public", catalog = "policlinic")
public class RecAnIdentity {
    @ManyToOne
    @JoinColumn(name = "id_reception", referencedColumnName = "id_reception", nullable = false)
    private Receptions receptionsByIdReception;
    @ManyToOne
    @JoinColumn(name = "id_analysis", referencedColumnName = "id_analysis", nullable = false)
    private Analyses analysesByIdAnalysis;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rec_an_identity")
    private int idRecAnIdentity;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        RecAnIdentity that = (RecAnIdentity) o;
//
//        if (idRecAnIdentity != that.idRecAnIdentity) return false;
//        if (receptionsByIdReception != that.receptionsByIdReception) return false;
//        if (analysesByIdAnalysis != that.analysesByIdAnalysis) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idRecAnIdentity;
//        result = 31 * result + analysesByIdAnalysis.getIdAnalysis();
//        result = 31 * result + receptionsByIdReception.getIdReception();
//        return result;
//    }

    public Receptions getReceptionsByIdReception() {
        return receptionsByIdReception;
    }

    public void setReceptionsByIdReception(Receptions receptionsByIdReception) {
        this.receptionsByIdReception = receptionsByIdReception;
    }

    public Analyses getAnalysesByIdAnalysis() {
        return analysesByIdAnalysis;
    }

    public void setAnalysesByIdAnalysis(Analyses analysesByIdAnalysis) {
        this.analysesByIdAnalysis = analysesByIdAnalysis;
    }

    public void setId(int idRecAnIdentity) {
        this.idRecAnIdentity = idRecAnIdentity;
    }

    public int getId() {
        return idRecAnIdentity;
    }
}
