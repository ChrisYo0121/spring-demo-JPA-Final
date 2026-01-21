package fcu.pbiecs.springdemo.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
