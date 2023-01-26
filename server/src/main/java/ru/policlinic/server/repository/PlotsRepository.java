package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.Plots;
import ru.policlinic.server.model.Specializations;

import java.util.Optional;

public interface PlotsRepository extends JpaRepository<Plots,Long> {
    Optional<Plots> findByPlotNumber(int plot);
}
