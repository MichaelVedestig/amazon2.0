package com.amazon.api.model;

import com.amazon.api.token.Token;

import java.util.List;

public record UserDTO(
        Integer id,
        String email,
        Role role,
        List<Token> tokens
) {
}
