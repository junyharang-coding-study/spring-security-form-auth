package com.junyharang.springsecurityformauth.repository.user;

import com.junyharang.springsecurityformauth.domain.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Long> {
}
