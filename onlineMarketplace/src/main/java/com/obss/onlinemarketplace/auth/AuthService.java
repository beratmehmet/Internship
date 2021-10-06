package com.obss.onlinemarketplace.auth;

import com.obss.onlinemarketplace.dto.UserVM;
import com.obss.onlinemarketplace.model.User;
import com.obss.onlinemarketplace.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthService {
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse authenticate(Credentials credentials) {
        User inDb = userRepository.findByUsername(credentials.getUsername());
        if (inDb == null) {
            throw new AuthException("username is incorrect");
        }
        boolean matches = passwordEncoder.matches(credentials.getPassword(), inDb.getPassword());
        if (!matches) {
            throw new AuthException("password is incorrect");
        }
        UserVM userVM = new UserVM(inDb);
        String tokenJWT = Jwts.builder().setSubject("" + inDb.getId()).signWith(SignatureAlgorithm.HS512, "my-app-secret").compact();
        AuthResponse response = new AuthResponse();
        response.setUser(userVM);
        response.setToken(tokenJWT);
        return response;
    }

    @Transactional
    public UserDetails getUserDetails(String tokenJWT) {

        JwtParser parser = Jwts.parser().setSigningKey("my-app-secret");
        try {
            parser.parse(tokenJWT);
            Claims claims = parser.parseClaimsJws(tokenJWT).getBody();
            long userId = new Long(claims.getSubject());
            User user = userRepository.getById(userId);
            User actualUser = (User) ((HibernateProxy) user).getHibernateLazyInitializer().getImplementation();
            return actualUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
