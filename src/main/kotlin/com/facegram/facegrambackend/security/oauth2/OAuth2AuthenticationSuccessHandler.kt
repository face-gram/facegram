package com.facegram.facegrambackend.security.oauth2

import com.facegram.facegrambackend.security.oauth2.jwt.JwtTokenProvider
import lombok.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

//OAuth2 로그인 성공시 호출되는 Handler
//로그인에 성공하면 JWT를 생성한 다음 authorizedRedirectUri로 client에게 전송한다
@ConstructorBinding
//1) SpringBoot 2.2.1 부터 지원되는 기능으로 따로 Component 어노테이션을 선언하지 않고 빈에 등록해주며 생성자 바인딩 시켜주는 어노테이션입니다.
//2) Application.yml 에서 prefix "sample"인 설정정보를 읽어옵니다.
//Application.yml 에 설정 키 값에 "-"가 들어갈 경우 camel 케이스로 작성해주시면 매핑시켜줍니다.
class OAuth2AuthenticationSuccessHandler(

    private val redirectUri: String,
    private val tokenProvider: JwtTokenProvider
): SimpleUrlAuthenticationSuccessHandler() {
}