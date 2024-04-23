package ru.buzynnikov.notemanager.security.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.buzynnikov.notemanager.security.models.User;

@Data
public class RegistrationForm {
    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password),"ROLE_USER");
    }
}
