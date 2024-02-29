package ru.effectivemobile.authservice.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.effectivemobile.authservice.utils.Status;

@Entity
@Table(name = "t_users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Status status;
}
