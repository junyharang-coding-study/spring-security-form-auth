package com.junyharang.springsecurityformauth.security.config;

import com.junyharang.springsecurityformauth.constant.ServiceURIManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity          // Spring Security 설정 활성화
@Configuration
public class WebSecurityConfigure {

    @Bean public BCryptPasswordEncoder passwordEncoder() {     /* Password Hash 암호화 기능 사용을 위한 객체 주입 */
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        /* Memory에 임시 사용자 계정 생성을 위한 객체 생성 */
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        /* Password 암호화 */
        String password = passwordEncoder.encode("1234");

        /* 사용자 ID 생성 */
        manager.createUser(User.withUsername("user")
                .password(password)
                .roles("USER")
                .build());

        manager.createUser(User.withUsername("manager")
                .password(password)
                .roles("MANAGER")
                .build());

        manager.createUser(User.withUsername("admin")
                .password(password)
                .roles("ADMIN")
                .build());

        return manager;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(ServiceURIManagement.NOW_VERSION + "/user/**").hasRole("USER")
                .antMatchers(ServiceURIManagement.NOW_VERSION + "/manager/**").hasRole("MANAGER")
                .antMatchers(ServiceURIManagement.NOW_VERSION + "/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin();

        return http.build();
    }
}
