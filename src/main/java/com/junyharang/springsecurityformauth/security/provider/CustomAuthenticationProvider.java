package com.junyharang.springsecurityformauth.security.provider;

import com.junyharang.springsecurityformauth.security.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired private UserDetailsService userDetailsService;
    @Autowired private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {      /* Password 및 추가 검증을 위한 Logic 처리 */

        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();

        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userId);

        if (!passwordEncoder.matches(password, userDetails.getMember().getPassword())) {
            throw new BadCredentialsException("비밀번호가 틀렸어요 🥲");
        }

        return new UsernamePasswordAuthenticationToken(userDetails.getMember(), null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {  /* authentication 객체 Type과 CustomAuthenticationProvider에서 이용하고자 하는 Token 값이 일치하는지 확인하고, 인증 처리를 할 수 있도록 구현 */
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
