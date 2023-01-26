package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.Cabinets;
import ru.policlinic.server.model.Plots;

import java.util.Optional;

public interface CabinetsRepository extends JpaRepository<Cabinets,Long> {
    Optional<Cabinets> findByCabinetNumber(String cabinet);
}
