package com.obss.onlinemarketplace.auth;

import com.obss.onlinemarketplace.dto.UserVM;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;

    private UserVM user;
}
