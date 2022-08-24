package com.junyharang.springsecurityformauth.security.config;

import com.junyharang.springsecurityformauth.constant.ServiceURIManagement;
import com.junyharang.springsecurityformauth.security.common.FormAuthenticationDetailsSource;
import com.junyharang.springsecurityformauth.security.handler.CustomAccessDeniedHandler;
import com.junyharang.springsecurityformauth.security.provider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity          // Spring Security 설정 활성화
@Configuration
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired private FormAuthenticationDetailsSource formAuthenticationDetailsSource;
    @Autowired private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean public AccessDeniedHandler accessDeniedHandler() {

        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");

        return accessDeniedHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http
                .authorizeRequests()
                .antMatchers("/", "/signup", "/signin/**", "/signin*").permitAll()
                .antMatchers(ServiceURIManagement.NOW_VERSION + "/user/**").hasRole("USER")
                .antMatchers(ServiceURIManagement.NOW_VERSION + "/manager/**").hasRole("MANAGER")
                .antMatchers(ServiceURIManagement.NOW_VERSION + "/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()

        .and()
                .formLogin()
                /* 개발자가 만든 로그인 Page 관련 설정 */
                .loginPage("/signin")
                .loginProcessingUrl("/signin_proc")
                .authenticationDetailsSource(formAuthenticationDetailsSource)
                .defaultSuccessUrl("/")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
        .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());

    }
}
