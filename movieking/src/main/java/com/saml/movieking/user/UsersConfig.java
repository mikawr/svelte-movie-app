package com.saml.movieking.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UsersConfig {

    @Bean
    CommandLineRunner commandLineRunner(UsersRepository repository) {
        return args -> {
            Users sam = new Users(
                    1L,
                    "Sam Smith",
                    "sam.smith@mail.com",
                    "password1",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Users thomas = new Users(
                    "Thomas Thompson",
                    "thomas.thompson@mail.com",
                    "password2",
                    LocalDate.of(2002, Month.DECEMBER, 10)
            );

            repository.saveAll(
                    List.of(sam, thomas)
            );
        };
    }
}
