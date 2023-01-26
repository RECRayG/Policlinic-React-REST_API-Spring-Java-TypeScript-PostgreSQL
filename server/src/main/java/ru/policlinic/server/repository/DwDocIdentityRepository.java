package ru.policlinic.server.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ru.policlinic.server.model.Doctors;
import ru.policlinic.server.model.DwDocIdentity;
import java.util.*;
import java.util.function.Function;

public interface DwDocIdentityRepository extends JpaRepository<DwDocIdentity,Long> {

}