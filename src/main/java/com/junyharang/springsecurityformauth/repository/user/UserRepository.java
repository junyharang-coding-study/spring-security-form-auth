package com.junyharang.springsecurityformauth.repository.user;

import com.junyharang.springsecurityformauth.domain.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
