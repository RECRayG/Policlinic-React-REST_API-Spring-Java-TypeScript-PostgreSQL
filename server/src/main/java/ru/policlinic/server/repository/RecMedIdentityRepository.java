package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.RecMedIdentity;

public interface RecMedIdentityRepository extends JpaRepository<RecMedIdentity, Long> {

}
