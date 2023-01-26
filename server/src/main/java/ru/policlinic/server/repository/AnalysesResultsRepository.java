package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.Analyses;
import ru.policlinic.server.model.AnalysesResults;
import ru.policlinic.server.model.Proceduress;

import java.util.Optional;

public interface AnalysesResultsRepository extends JpaRepository<AnalysesResults, Long> {
    Optional<AnalysesResults> findByAnalysisResult(String analysisResult);
    Optional<AnalysesResults> findByAnalysesByIdAnalysis(Analyses analysesByIdAnalysis);
}
