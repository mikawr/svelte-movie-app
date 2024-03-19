package com.saml.movieking.user;

import com.google.common.hash.Hashing;
import com.saml.movieking.security.jwt.JwtResponse;
import com.saml.movieking.security.jwt.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import javax.naming.Name;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtils jwtUtils;

    public ResponseEntity<?> registerUser(Users user) {
        return registerUser(user, passwordEncoder);
    }

    public ResponseEntity<?> registerUser(Users user, PasswordEncoder passwordEncoder) {
        Name dn = /*(Name)*/ LdapNameBuilder.newInstance().add("cn", user.getName()).build();
        DirContextAdapter context = new DirContextAdapter(String.valueOf(dn));

        context.setAttributeValues("objectClass", new String[]{"simpleSecurityObject", "organizationalPerson"});
        context.setAttributeValue("cn", user.getName());
        context.setAttributeValue("sn", user.getName());
        context.setAttributeValue("userPassword", passwordEncoder.encode(user.getPassword()));

        //System.out.println("\n"+ user.getPassword() + " - " + passwordEncoder.encode(user.getPassword()) + "\n");

        ldapTemplate.bind(context);
        return ResponseEntity.ok("User registered successfully!");
    }

    public ResponseEntity<?> loginUser(Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        LdapUserDetailsImpl userDetails = (LdapUserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders
                .add(HttpHeaders.SET_COOKIE, "token=" + jwt + "; Path=/; HttpOnly; SameSite=None; Secure");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new JwtResponse(jwt,
                        0L,
                        userDetails.getUsername(),
                        userDetails.getDn(),
                        roles));
    }

    public String logoutUser(Users user) {
        return "redirect:/";
    }
}
