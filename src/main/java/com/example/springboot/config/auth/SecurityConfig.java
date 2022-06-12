package com.example.springboot.config.auth;

import com.example.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        google cloud 에서 승인된 리디렉션을 지정함. 해당 리디렉션 /login/oauth2/code/google 은 스프링 시큐리티에서 기본 제공하는 URL
        처음 실행해서 spring framework 이 시작될때 설정 됨.
         */

        http.csrf().disable().headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout().logoutSuccessUrl("/")
                .and()
                    .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
                    //oauth2Login - OAuth2 로그인 기능에 대한 여러 설정의 진입점
                    //userInfoEndPoint - OAuth2 로그인 후 사용저 정보를 가져올 때의 설정
                    //userService - 소셜 로그인 성공 후 후속조치 진행 (가져온 사용자 정보를 바탕으로 추가 진행 기능 명시)
    }
}
