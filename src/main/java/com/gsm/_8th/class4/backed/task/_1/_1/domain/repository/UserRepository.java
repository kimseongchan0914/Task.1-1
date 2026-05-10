package com.gsm._8th.class4.backed.task._1._1.domain.repository;


import com.gsm._8th.class4.backed.task._1._1.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // email으로 사용자 찾기
    Optional<User> findByEmail(String email);

    // username으로 사용자 찾기
    Optional<User> findByUsername(String username);

    // 이메일이 이미 존재하는지 확인
    boolean existsByEmail(String email);

    // username이 이미 존재하는지 확인
    boolean existsByUsername(String username);
}