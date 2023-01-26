package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.Analyses;
import ru.policlinic.server.model.Proceduress;

import java.util.Optional;

public interface AnalysesRepository extends JpaRepository<Analyses, Long> {
    Optional<Analyses> findByAnalysisName(String analysisName);
}
