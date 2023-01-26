package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.Medications;
import ru.policlinic.server.model.Proceduress;

import java.util.Optional;

public interface MedicationsRepository extends JpaRepository<Medications, Long> {
    Optional<Medications> findByMedicationName(String medicationName);
}
