package br.com.dmms.threads.user.controller.dto;

import java.time.LocalDateTime;

public record UserDTO(
        String id,
        String name,
        String email,
        boolean active,
        LocalDateTime createdAt
) {
}
