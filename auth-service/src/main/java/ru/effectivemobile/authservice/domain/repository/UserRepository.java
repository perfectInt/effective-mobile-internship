package ru.effectivemobile.authservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.authservice.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
