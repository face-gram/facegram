package com.facegram.facegrambackend.security.oauth2.user


abstract class OAuth2UserInfo(
    open val attributes: Map<String,Any?>
) {

    fun getAttributes(): Map<String,Any?>{
        return attributes
    }

    abstract fun getId(): String
    abstract fun getName(): String
    abstract fun getEmail(): String
    abstract fun getImageUrl(): String


}