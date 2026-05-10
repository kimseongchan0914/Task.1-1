package com.gsm._8th.class4.backed.task._1._1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String username;
    private String password;
    private String passwordConfirm;

    public boolean passwordMatches() {
        return password.equals(passwordConfirm);
    }
}