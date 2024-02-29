package ru.effectivemobile.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.effectivemobile.authservice.domain.entity.Code;
import ru.effectivemobile.authservice.domain.entity.User;
import ru.effectivemobile.authservice.domain.repository.CodeRepository;
import ru.effectivemobile.authservice.domain.repository.UserRepository;
import ru.effectivemobile.authservice.dto.LoginDto;
import ru.effectivemobile.authservice.dto.RegistrationDto;
import ru.effectivemobile.authservice.dto.TokenDto;
import ru.effectivemobile.authservice.exception.ExpiredTokenException;
import ru.effectivemobile.authservice.exception.IncorrectCodeException;
import ru.effectivemobile.authservice.exception.ObjectAlreadyExistsException;
import ru.effectivemobile.authservice.exception.ObjectNotFoundException;
import ru.effectivemobile.authservice.security.JwtService;
import ru.effectivemobile.authservice.utils.Status;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CodeRepository codeRepository;
    private final JwtService jwtService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public void register(RegistrationDto registrationDto) {
        String email = registrationDto.getEmail();
        if (userRepository.existsByEmail(email)) throw new ObjectAlreadyExistsException();
        User user = new User();
        user.setEmail(email);
        user.setStatus(Status.PENDING);
        user = userRepository.save(user);
        generateCode(user);
    }

    public TokenDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(ObjectNotFoundException::new);
        Code code = codeRepository.findByUser(user).orElseThrow(ObjectNotFoundException::new);
        if (!Objects.equals(code.getCode(), loginDto.getCode())) {
            throw new IncorrectCodeException();
        }
        if (LocalDateTime.now().isAfter(code.getExpiresIn()))
            throw new ExpiredTokenException();
        String token = createToken(loginDto.getEmail());
        return new TokenDto(token);
    }

    private String createToken(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(ObjectNotFoundException::new);
        user.setStatus(Status.ACTIVATED);
        userRepository.save(user);
        return jwtService.generateAccessToken(user);
    }

    private void generateCode(User user) {
        Code code = new Code();
        code.setUser(user);
        code.setExpiresIn(LocalDateTime.now().plusHours(2));
        code.setCode(new Random().nextInt(9000) + 1000);
        codeRepository.save(code);
        kafkaTemplate.send("verify-code", "new code " + code.getCode() + " for user " + user.getEmail());
    }
}
