package com.saml.movieking.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public void registerUser(Users user) {
        Optional<Users> nameOptional = usersRepository.findUserByName(user.getName());
        Optional<Users> emailOptional = usersRepository.findUserByEmail(user.getEmail());

        if (nameOptional.isPresent() || emailOptional.isPresent()) {
            throw new IllegalStateException("username or email taken");
        } else {
            usersRepository.save(user);
        }
    }

    public void loginUser(Users user) {
        Optional<Users> emailOptional = usersRepository.findUserByEmail(user.getEmail());

        if (emailOptional.isEmpty()) {
            throw new IllegalStateException("user not found");
        } else {
            Optional<Users> passwordOptional = usersRepository.getUserPasswordByEmail(user.getEmail());

            if (passwordOptional.isEmpty()) {
                throw new IllegalStateException("password not found");
            } else {
                String password = String.valueOf(passwordOptional.get());

                if (password.equals(user.getPassword())) {
                    System.out.println("Login successful");
                } else {
                    throw new IllegalStateException("password incorrect");
                }
            }
        }
    }

    public void deleteUser(Long userId) {
        boolean exists = usersRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException(
                    "user with id " + userId + " does not exists"
            );
        }

        usersRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId,
                           String name,
                           String email,
                           String password,
                           String dob) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id " + userId + " does not exists"
                ));

        if (name != null && !name.isEmpty() && !name.equals(user.getName())) {
            user.setName(name);
        }

        if (email != null && !email.isEmpty() && !email.equals(user.getEmail())) {
            Optional<Users> userOptional = usersRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }

        if (password != null && !password.isEmpty() && !password.equals(user.getPassword())) {
            user.setPassword(password);
        }

        if (dob != null && !dob.isEmpty()) {
            user.setDob(LocalDate.parse(dob));
        }
    }
}
