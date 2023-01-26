package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.Patients;

public interface PatientsRepository extends JpaRepository<Patients,Long> {

}
