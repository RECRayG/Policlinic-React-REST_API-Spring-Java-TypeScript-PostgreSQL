package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.Doctors;
import ru.policlinic.server.model.Receptions;
import ru.policlinic.server.model.TimesOfJob;

import java.awt.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ReceptionsRepository extends JpaRepository<Receptions,Long> {
    Optional<List<Receptions>> findByDateOfReception(Date dateOfReception);
}
