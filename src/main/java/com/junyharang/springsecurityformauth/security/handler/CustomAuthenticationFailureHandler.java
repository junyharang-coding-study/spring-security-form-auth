package com.junyharang.springsecurityformauth.security.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "Please check your ID and password.";

        if (exception instanceof BadCredentialsException) {  /* 인증 요청자가 비밀번호를 틀렸을 경우 */
            errorMessage = "Please check the account information.";
        } else if (exception instanceof InsufficientAuthenticationException) {  /* 인증 요청자가 부가적으로 인증 시 제공할 값이 틀렸을 경우 */
            errorMessage  = "Please check the additional verification value.";
        }
        /* 인증 실패 시 이동할 경로와 Error Message가 보이게 처리 */
        setDefaultFailureUrl("/signin?error=true&exception=" + errorMessage);
        /* 부모 Class(SimpleUrlAuthenticationFailureHandler)에게 인증 실패 작업 위임 */
        super.onAuthenticationFailure(request, response, exception);
    }
}
