package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.TimesOfJob;
import ru.policlinic.server.model.Users;

import java.time.LocalTime;
import java.util.Optional;

public interface TimeOfJobRepository extends JpaRepository<TimesOfJob,Long> {
    Optional<TimesOfJob> findByTimeValue(LocalTime timeValue);
}
