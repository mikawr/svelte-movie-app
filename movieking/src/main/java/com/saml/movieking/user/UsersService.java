package com.saml.movieking.user;

import com.saml.movieking.security.jwt.JwtResponse;
import com.saml.movieking.security.jwt.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtils jwtUtils;

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
    public ResponseEntity<?> loginUser(Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UsersDetailsImpl userDetails = (UsersDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(new JwtResponse(jwt,
                                                 userDetails.getId(),
                                                 userDetails.getUsername(),
                                                 userDetails.getEmail(),
                                                 roles));
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
