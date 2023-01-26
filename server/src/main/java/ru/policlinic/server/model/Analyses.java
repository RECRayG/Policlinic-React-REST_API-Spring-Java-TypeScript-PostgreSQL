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
@Table(name = "analyses", schema = "public", catalog = "policlinic")
public class Analyses implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_analysis")
    private int idAnalysis;
    @Basic
    @Column(name = "analysis_name")
    private String analysisName;
    @OneToMany(mappedBy = "analysesByIdAnalysis", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<AnalysesResults> analysesResultsByIdAnalysis;
    @OneToMany(mappedBy = "analysesByIdAnalysis", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<RecAnIdentity> recAnIdentitiesByIdAnalysis;

    public int getIdAnalysis() {
        return idAnalysis;
    }

    public void setIdAnalysis(int idAnalysis) {
        this.idAnalysis = idAnalysis;
    }

    public String getAnalysisName() {
        return analysisName;
    }

    public void setAnalysisName(String analysisName) {
        this.analysisName = analysisName;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Analyses analyses = (Analyses) o;
//
//        if (idAnalysis != analyses.idAnalysis) return false;
//        if (analysisName != null ? !analysisName.equals(analyses.analysisName) : analyses.analysisName != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idAnalysis;
//        result = 31 * result + (analysisName != null ? analysisName.hashCode() : 0);
//        return result;
//    }

    public Collection<AnalysesResults> getAnalysesResultsByIdAnalysis() {
        return analysesResultsByIdAnalysis;
    }

    public void setAnalysesResultsByIdAnalysis(Collection<AnalysesResults> analysesResultsByIdAnalysis) {
        this.analysesResultsByIdAnalysis = analysesResultsByIdAnalysis;
    }

    public Collection<RecAnIdentity> getRecAnIdentitiesByIdAnalysis() {
        return recAnIdentitiesByIdAnalysis;
    }

    public void setRecAnIdentitiesByIdAnalysis(Collection<RecAnIdentity> recAnIdentitiesByIdAnalysis) {
        this.recAnIdentitiesByIdAnalysis = recAnIdentitiesByIdAnalysis;
    }
}
