package br.com.dmms.threads.user.service;

import br.com.dmms.threads.user.model.User;
import br.com.dmms.threads.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(String name, String username, String password) {
        User user = new User(name, username, passwordEncoder.encode(password));
        user.setActive(true);
        user.setRole("USER");
        log.debug("creating user...");
        return userRepository.save(user);
    }

    public Optional<User> getById(String id) {
        log.debug("fetching user {}", id);
        return userRepository.findById(id);
    }

}
