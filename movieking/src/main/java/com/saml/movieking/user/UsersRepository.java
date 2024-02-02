package com.saml.movieking.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Optional<Users> findUserByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.name = ?1")
    Optional <Users> findUserByName(String name);

    @Query("SELECT u.password FROM Users u WHERE u.email = ?1")
    Optional <Users> getUserPasswordByEmail(String password);
}
