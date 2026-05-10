package com.gsm._8th.class4.backed.task._1._1.domain.service;


import com.gsm._8th.class4.backed.task._1._1.domain.dto.SignInRequestDto;
import com.gsm._8th.class4.backed.task._1._1.domain.dto.SignUpRequestDto;
import com.gsm._8th.class4.backed.task._1._1.domain.dto.SignInResponseDto;
import com.gsm._8th.class4.backed.task._1._1.domain.entity.Role;
import com.gsm._8th.class4.backed.task._1._1.domain.entity.User;
import com.gsm._8th.class4.backed.task._1._1.domain.repository.UserRepository;
import com.gsm._8th.class4.backed.task._1._1.domain.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequestDto request) {
        if (!request.passwordMatches()) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 username입니다");
        }

        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }

    public SignInResponseDto signIn(SignInRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        String token = jwtProvider.generateToken(user.getEmail());

        return SignInResponseDto.of(user, token);
    }
}