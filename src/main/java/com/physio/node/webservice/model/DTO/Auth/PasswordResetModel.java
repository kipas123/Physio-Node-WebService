package com.physio.node.webservice.model.DTO.Auth;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class PasswordResetModel {
    private String token;
    private String password;

    public PasswordResetModel(String token, String password) {
        this.token = token;
        this.password = password;
    }

}
