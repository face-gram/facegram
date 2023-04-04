package com.facegram.facegrambackend.security.oauth2

import com.facegram.facegrambackend.domain.user.User
import com.facegram.facegrambackend.domain.user.UserRepository
import com.facegram.facegrambackend.domain.user.UserRole
import com.facegram.facegrambackend.security.CustomUserDetails
import com.facegram.facegrambackend.security.oauth2.user.AuthProvider
import com.facegram.facegrambackend.security.oauth2.user.OAuth2UserInfo
import com.facegram.facegrambackend.security.oauth2.user.OAuth2UserInfoFactory
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service


//loadUser()를 오버라이드해서 OAuth2UserRequest에 있는 Access Token으로 유저정보를 얻는다
//획득한 유저정보를 process()에서 Java Model과 맵핑하고 가입 되지 않은 경우와 이미 가입된 경우를 구분하여 프로세스르 진행한다
//결과로 OAuth2User를 구현한 CustomUserDetails 객체를 생성한다
@Service
class CustomOAuth2UserService
constructor(
    private val userRepository: UserRepository
): DefaultOAuth2UserService(){
    // OAuth2UserRequest에 있는 Access Token으로 유저정보 get

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val oAuth2User: OAuth2User = super.loadUser(userRequest)
        return process(userRequest,oAuth2User)
    }

    private fun process(userRequest: OAuth2UserRequest?,
                        oAuth2User: OAuth2User): OAuth2User {
        val authProvider: AuthProvider = AuthProvider.valueOf(
            userRequest?.clientRegistration?.registrationId?.uppercase()?:
            throw IllegalArgumentException("AuthProvider Error")
        )

        val userInfo: OAuth2UserInfo = OAuth2UserInfoFactory.getOAuthUserInfo(authProvider,oAuth2User.attributes)

        if(userInfo.getEmail().isEmpty()){
            throw IllegalArgumentException("Email이 OAuth 프로바이더 측에 없습니다.")
        }
        val userOptional: User? = userRepository.findByEmail(userInfo.getEmail())


        // 이미 가입된 사람
        if(userOptional != null){
            if(authProvider != userOptional.authProvider){
                throw IllegalArgumentException("AuthProvider가 다릅니다.")
            }
            val user: User = userOptional

            return CustomUserDetails.create(user,oAuth2User.attributes)
        }else{
            // 가입되지 않은 사람
            val user = createUser(userInfo,authProvider)

            return CustomUserDetails.create(user,oAuth2User.attributes)
        }

    }

    private fun createUser(userInfo: OAuth2UserInfo, authProvider: AuthProvider): User {
        val user = User.newInstance(
            userInfo.getName(),
            null,
            userInfo.getEmail(),
            userInfo.getImageUrl(),
            UserRole.USER,
            null,
            authProvider
        )
        return userRepository.save(user)
    }


}