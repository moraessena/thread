package br.com.dmms.threads.user.controller;

import br.com.dmms.threads.user.controller.dto.CreateUserDTO;
import br.com.dmms.threads.user.controller.dto.UserDTO;
import br.com.dmms.threads.user.model.User;
import br.com.dmms.threads.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    UserDTO create(@RequestBody CreateUserDTO dto) {
        User user = userService.create(dto.name(), dto.email(), dto.password());
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.isActive(), user.getCreatedAt());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO getById(@PathVariable String id, @AuthenticationPrincipal UserDetails userDetails) {
        log.info("current user is {}", userDetails.getUsername());
        return userService.getById(id).map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.isActive(), user.getCreatedAt())).orElse(null);
    }
}
