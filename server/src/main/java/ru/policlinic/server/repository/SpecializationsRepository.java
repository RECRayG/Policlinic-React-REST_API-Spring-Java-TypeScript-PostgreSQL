package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.Specializations;
import ru.policlinic.server.model.Users;

import java.util.Optional;

public interface SpecializationsRepository extends JpaRepository<Specializations,Long> {
    Optional<Specializations> findBySpecializationName(String specialization);
}
