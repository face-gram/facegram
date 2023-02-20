package com.facegram.facegrambackend.security

import com.facegram.facegrambackend.domain.user.User
import lombok.Getter
import lombok.Setter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User
import java.util.*
import java.util.Collections.*


// spring security가 필요로하는 Authentication 객체를 커스텀한 것
// 이걸 토대로 spring security가 정보를 파악한다.
@Getter
@Setter
class CustomUserDetails(
    private val id: Long?,
    private val username: String,
    private val authorities: Collection<GrantedAuthority>,
    private var attributes: MutableMap<String, Any>? = null

):UserDetails, OAuth2User{

    // 오브젝트 마더 패턴
    companion object{
        private fun create(user: User): CustomUserDetails{
            val authorities: MutableCollection<out GrantedAuthority> = singletonList(SimpleGrantedAuthority("ROLE_USER"))

            return CustomUserDetails(
                user.id,
                user.username,
                authorities
            )
        }

        fun create(user: User,attributes: MutableMap<String, Any>?): CustomUserDetails{
            val userDetails: CustomUserDetails = this.create(user)
            userDetails.attributes = attributes
            return userDetails
        }
    }


    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }


    override fun getPassword(): String? {
        return null
    }
    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }


    // OAUTH2에서 원하는 정보
    override fun getName(): String {
        return id.toString()
    }

    override fun getAttributes(): MutableMap<String, Any>? {
        return attributes
    }
}
