package ru.effectivemobile.authservice.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private Integer code;
}
