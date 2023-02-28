package com.facegram.facegrambackend.config

import com.facegram.facegrambackend.security.oauth2.CookieAuthorizationRequestRepository
import com.facegram.facegrambackend.security.oauth2.CustomOAuth2UserService
import com.facegram.facegrambackend.security.oauth2.OAuth2AuthenticationFailureHandler
import com.facegram.facegrambackend.security.oauth2.OAuth2AuthenticationSuccessHandler
import com.facegram.facegrambackend.security.oauth2.jwt.JwtAccessDeniedHandler
import com.facegram.facegrambackend.security.oauth2.jwt.JwtAuthenticationEntryPoint
import com.facegram.facegrambackend.security.oauth2.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Configuration
@EnableWebSecurity
class SecurityConfig(

    private val customOAuth2UserService: CustomOAuth2UserService,
    private val cookieAuthorizationRequestRepository: CookieAuthorizationRequestRepository,
    private val authenticationSuccessHandler: OAuth2AuthenticationSuccessHandler,
    private val authenticationFailureHandler: OAuth2AuthenticationFailureHandler,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
) {
    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer? {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().antMatchers("/h2-console/**\",\"/favicon.ico")
        }
    }
//        return (web) -> web?.ignoring()?.antMatchers("/h2-console/**","/favicon.ico")
     @Bean
     fun configure(http: HttpSecurity):SecurityFilterChain {
        http.authorizeHttpRequests()
            .antMatchers("/h2-console/**").permitAll()
            // 여기서 풀어줄 거만 적자 .permitAll()해버리면 된다.
            .antMatchers("/oauth2/**","/auth/**").permitAll()
            .anyRequest().authenticated()


        http.cors() // CORS on
            .and()
            .csrf().disable() // CSRF off
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
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    // Session off

        http.formLogin().disable()
            .oauth2Login()
                //이 서버는 두 가지 endpoint를 노출시키고 있다.
                //Authorization endpoint : 사용자 인증 및 동의를 처리하기 위해 사용자와 상호작용하는 엔드포인트
                //Token endpoint : 기계와 기계가 상호작용하는 엔드포인트
                // 즉 어소라이제이션 엔드포인트는 프론트에서 백으로 소셜로그인 요청을 보내는 URL이다.
                .authorizationEndpoint()
                    .baseUri("/oauth2/authorize")
                    .authorizationRequestRepository(cookieAuthorizationRequestRepository)
                .and()
                .redirectionEndpoint()
                    .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                .and()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 401
                .accessDeniedHandler(jwtAccessDeniedHandler)// 403
        http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter::class.java)
         return http.build()
    }
}