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
@Table(name = "analyses_results", schema = "public", catalog = "policlinic")
public class AnalysesResults {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_analysis_result")
    private int idAnalysisResult;
    @Basic
    @Column(name = "analysis_result")
    private String analysisResult;
    @ManyToOne
    @JoinColumn(name = "id_analysis", referencedColumnName = "id_analysis", nullable = false)
    private Analyses analysesByIdAnalysis;
    @ManyToOne
    @JoinColumn(name = "id_patient", referencedColumnName = "id_patient", nullable = false)
    private Patients patientsByIdPatient;

    public int getIdAnalysisResult() {
        return idAnalysisResult;
    }

    public void setIdAnalysisResult(int idAnalysisResult) {
        this.idAnalysisResult = idAnalysisResult;
    }

    public String getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(String analysisResult) {
        this.analysisResult = analysisResult;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        AnalysesResults that = (AnalysesResults) o;
//
//        if (idAnalysisResult != that.idAnalysisResult) return false;
//        if (analysesByIdAnalysis != that.analysesByIdAnalysis) return false;
//        if (patientsByIdPatient != that.patientsByIdPatient) return false;
//        if (analysisResult != null ? !analysisResult.equals(that.analysisResult) : that.analysisResult != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idAnalysisResult;
//        result = 31 * result + analysesByIdAnalysis.getIdAnalysis();
//        result = 31 * result + patientsByIdPatient.getIdPatient();
//        result = 31 * result + (analysisResult != null ? analysisResult.hashCode() : 0);
//        return result;
//    }

    public Analyses getAnalysesByIdAnalysis() {
        return analysesByIdAnalysis;
    }

    public void setAnalysesByIdAnalysis(Analyses analysesByIdAnalysis) {
        this.analysesByIdAnalysis = analysesByIdAnalysis;
    }

    public Patients getPatientsByIdPatient() {
        return patientsByIdPatient;
    }

    public void setPatientsByIdPatient(Patients patientsByIdPatient) {
        this.patientsByIdPatient = patientsByIdPatient;
    }
}
