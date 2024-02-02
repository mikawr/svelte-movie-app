package com.saml.movieking.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Entity
@Table
@Data
public class Users {
    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate dob;

    public Users() {
    }

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Users(Long id, String name, String email, String password, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public Users(String name, String email, String password, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                '}';
    }
}
