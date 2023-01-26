package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.Doctors;

public interface DoctorsRepository extends JpaRepository<Doctors,Long> {

}
