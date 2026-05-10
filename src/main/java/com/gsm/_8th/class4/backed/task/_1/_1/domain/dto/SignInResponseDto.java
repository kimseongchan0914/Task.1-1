package com.gsm._8th.class4.backed.task._1._1.domain.dto;

import com.gsm._8th.class4.backed.task._1._1.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInResponseDto {
    private Long id;
    private String email;
    private String username;
    private String token;

    public static SignInResponseDto of(User user, String token) {
        return SignInResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .token(token)
                .build();
    }
}