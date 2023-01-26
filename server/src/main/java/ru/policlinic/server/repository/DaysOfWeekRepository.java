package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.DaysOfWeek;
import ru.policlinic.server.model.TimesOfJob;

import java.util.Optional;

public interface DaysOfWeekRepository extends JpaRepository<DaysOfWeek, Long> {
    Optional<DaysOfWeek> findByDayOfWeek(String dayOfWeek);
}
