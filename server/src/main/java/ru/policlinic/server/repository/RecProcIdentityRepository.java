package ru.policlinic.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.policlinic.server.model.RecProcIdentity;

public interface RecProcIdentityRepository extends JpaRepository<RecProcIdentity, Long> {

}
