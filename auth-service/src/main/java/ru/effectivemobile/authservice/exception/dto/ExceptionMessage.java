package ru.effectivemobile.authservice.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionMessage {
    private Boolean success;
    private String message;
}
