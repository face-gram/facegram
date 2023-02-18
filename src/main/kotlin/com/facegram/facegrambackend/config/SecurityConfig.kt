package com.facegram.facegrambackend.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityConfig(): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
                // formlogin 안 쓴다.
                .formLogin().disable()
                // 기존에 있는 걸 꺼버리겠다.
                // 기본적인 http를 안 쓰겠다. 쿠키는 기본적으로 동일 도메인에서 요청을 하면 쿠키가 안 날라간다.
                // 쿠키는 http only로 설정이 되어서 클라이언트 코드(자바스크립트)에서 접근이 불가능하다.
                // http only를 풀어주면 보안이 떨어진다.
                // 세션은 서버 확장성이 떨어진다.
                // header에 Authorization: "Bearer: fdfdasfwfw" 이렇게 넣는 것이 jwt
                // http basic은 header에 Authorization: ID, PW 형식으로 사용한다.
                // 하지만 이걸 사용하면 암호화를 할 수 없어 보안이 떨어진다.
                // https 서버를 쓰면 암호화가 가능하다.
                // 그래서 httpBasic을 disable하는 것이다.
                // 토큰에는 유효시간이 존재한다.
                .httpBasic().disable()

            .oauth2Login()
            .userInfoEndpoint()





    }
}