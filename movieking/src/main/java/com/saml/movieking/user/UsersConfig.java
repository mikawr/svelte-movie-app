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
                    "$2y$10$O1ozSKEumtmbN22Ac0A0iOYBSk9NFUWs89tqVmYDim.J3rMPlcOIS",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Users thomas = new Users(
                    "Mika Werner",
                    "mika@mail.com",
                    "$2a$10$OF4p5ActaUreKv5tHptuO.FyNQoh5yN.N7/DyY1xqAyP/5Oe8ZxBm",
                    LocalDate.of(2002, Month.DECEMBER, 10)
            );

            repository.saveAll(
                    List.of(sam, thomas)
            );
        };
    }
}
