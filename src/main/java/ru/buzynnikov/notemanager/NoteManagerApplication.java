package ru.buzynnikov.notemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.buzynnikov.notemanager.security.models.User;
import ru.buzynnikov.notemanager.security.repositories.UserRepository;

@SpringBootApplication
public class NoteManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteManagerApplication.class, args);
	}

}
