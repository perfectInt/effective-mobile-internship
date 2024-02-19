package ru.effectivemobile.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.effectivemobile.authservice.dto.LoginDto;
import ru.effectivemobile.authservice.dto.RegistrationDto;
import ru.effectivemobile.authservice.dto.TokenDto;
import ru.effectivemobile.authservice.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/register")
    public void register(@RequestBody RegistrationDto registrationDto) {
        authService.register(registrationDto);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @GetMapping("/secured")
    public String testEndpoint() {
        return "secured endpoint";
    }
}
