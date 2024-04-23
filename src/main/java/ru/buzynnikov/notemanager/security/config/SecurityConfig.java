package ru.buzynnikov.notemanager.security.config;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.buzynnikov.notemanager.security.models.User;
import ru.buzynnikov.notemanager.security.repositories.UserRepository;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            Optional<User> user = userRepository.findByUsername(username);
            return user.orElseThrow(()-> new UsernameNotFoundException("User " + username + " not found"));
        };
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests(auth->auth
                    .requestMatchers("/notes/update/**","/notes/delete/**").hasRole("ADMIN")
                    .requestMatchers("/notes/**")  .hasAnyRole("ADMIN","USER")
                    .requestMatchers("/**").permitAll())
                .formLogin(x->x.defaultSuccessUrl("/notes",true))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


}
