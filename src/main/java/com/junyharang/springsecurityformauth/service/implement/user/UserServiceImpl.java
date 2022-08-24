package com.junyharang.springsecurityformauth.service.implement.user;

import com.junyharang.springsecurityformauth.domain.dto.request.SignUpRequestDTO;
import com.junyharang.springsecurityformauth.domain.entity.user.Member;
import com.junyharang.springsecurityformauth.repository.user.UserRepository;
import com.junyharang.springsecurityformauth.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        /* Logout 하려는 이용자 정보가 담긴 인증 객체 가져오기 */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {   /* 인증 객체가 Null이 아닌 경우 즉, 이용자가 인증한 상태인 경우 */
            /* SecurityContextLogoutHandler 이용하여 Logout 처리 */
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }

    @Override
    public void signIn(String error, String exception, Model model) {

        /* model 객체에 error와 exception 내용 담아 전달 */
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

    }
}
