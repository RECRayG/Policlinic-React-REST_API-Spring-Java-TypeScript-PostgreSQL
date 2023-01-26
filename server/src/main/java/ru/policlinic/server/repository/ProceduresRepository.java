package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.Proceduress;
import ru.policlinic.server.model.Specializations;

import java.util.Optional;

public interface ProceduresRepository extends JpaRepository<Proceduress, Long> {
    Optional<Proceduress> findByProcedureName(String procedureName);
}
