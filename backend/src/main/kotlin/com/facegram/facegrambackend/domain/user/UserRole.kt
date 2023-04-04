package com.facegram.facegrambackend.domain.user

import lombok.Getter

@Getter
enum class UserRole(
    val role: String,
    val short: String
) {
    ADMIN("ROLE_ADMIN", "admin"),
    USER("ROLE_USER", "user")

}
