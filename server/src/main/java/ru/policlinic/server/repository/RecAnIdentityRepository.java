package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.RecAnIdentity;

public interface RecAnIdentityRepository extends JpaRepository<RecAnIdentity, Long> {

}
