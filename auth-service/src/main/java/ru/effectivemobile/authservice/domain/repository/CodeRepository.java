package ru.effectivemobile.authservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.authservice.domain.entity.Code;
import ru.effectivemobile.authservice.domain.entity.User;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long> {

    Optional<Code> findByUser(User user);
}
