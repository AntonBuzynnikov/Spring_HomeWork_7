package ru.buzynnikov.notemanager.security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.buzynnikov.notemanager.security.dto.RegistrationForm;
import ru.buzynnikov.notemanager.security.models.User;
import ru.buzynnikov.notemanager.security.repositories.UserRepository;

@Controller
//@RequestMapping("/")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm(){
        userRepository.save(new User("admin2",
                "$2a$05$1dn0dnR5qhJP0D5Uk9mPnuHL89mRMA3MXX9QU2Tj827KBTqenc2zy",
                "ROLE_ADMIN"));
        return "registration";
    }
    @PostMapping
    public String processRegistration(RegistrationForm registrationFrom){
        userRepository.save(registrationFrom.toUser(passwordEncoder));
        System.out.println("Пользователь зарегистрирован");
        return "redirect:/login";
    }
}
