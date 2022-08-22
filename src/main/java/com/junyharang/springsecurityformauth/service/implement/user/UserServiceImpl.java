package com.junyharang.springsecurityformauth.service.implement.user;

import com.junyharang.springsecurityformauth.domain.dto.request.SignUpRequestDTO;
import com.junyharang.springsecurityformauth.domain.entity.user.Member;
import com.junyharang.springsecurityformauth.repository.user.UserRepository;
import com.junyharang.springsecurityformauth.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(SignUpRequestDTO signUpRequestDTO) {

        Member member = Member.builder()
                .username(signUpRequestDTO.getUsername())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .email(signUpRequestDTO.getEmail())
                .age(signUpRequestDTO.getAge())
                .role(signUpRequestDTO.getRole())
                .build();

        userRepository.save(member);
    }
}
