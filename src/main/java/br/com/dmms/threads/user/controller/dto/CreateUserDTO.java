package br.com.dmms.threads.user.controller.dto;

public record CreateUserDTO(
        String name,
        String email,
        String password
) {
}
