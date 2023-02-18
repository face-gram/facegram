package com.facegram.facegrambackend.security.oauth2.user

import com.facegram.facegrambackend.security.oauth2.user.AuthProvider.*

class OAuth2UserInfoFactory {

    companion object{
        fun getOAuthUserInfo(authProvider: AuthProvider, attributes: Map<String,Any?>)
        : OAuth2UserInfo{
            when(authProvider){
                GOOGLE -> return GoogleOAuth2UserInfo(attributes)
                else -> throw IllegalArgumentException("Invalid Provider Type")
            }
        }
    }
}