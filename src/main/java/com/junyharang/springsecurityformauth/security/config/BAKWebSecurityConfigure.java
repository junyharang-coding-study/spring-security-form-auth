package com.junyharang.springsecurityformauth.security.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity          // Spring Security 설정 활성화
//@Configuration
//public class WebSecurityConfigure {
//
//    @Bean public BCryptPasswordEncoder passwordEncoder() {     /* Password Hash 암호화 기능 사용을 위한 객체 주입 */
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
//
//        return http.build();
//    }
//
//    @Bean public WebSecurityCustomizer webSecurityCustomizer() {
//        /* /resources 하위 Directory는 보안 심사를 무시하고, 통과 시킨다. */
//        return (web) -> web.ignoring().antMatchers("/resources/**");
//    }

    //    @Bean public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
//        /* Memory에 임시 사용자 계정 생성을 위한 객체 생성 */
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        /* Password 암호화 */
//        String password = passwordEncoder.encode("1234");
//
//        /* 사용자 ID 생성 */
//        manager.createUser(User.withUsername("user")
//                .password(password)
//                .roles("USER")
//                .build());
//
//        manager.createUser(User.withUsername("manager")
//                .password(password)
//                .roles("USER", "MANAGER")
//                .build());
//
//        manager.createUser(User.withUsername("admin")
//                .password(password)
//                .roles("USER", "MANAGER", "ADMIN")
//                .build());
//
//        return manager;
//    }
//}
