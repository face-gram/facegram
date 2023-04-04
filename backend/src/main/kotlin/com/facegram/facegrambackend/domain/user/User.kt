package com.facegram.facegrambackend.domain.user

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.security.oauth2.user.AuthProvider
import lombok.NoArgsConstructor
import java.lang.IllegalArgumentException
import javax.persistence.*

@Entity
@NoArgsConstructor
class User constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var username: String,

    @Column(unique = true)
    val email: String,

    @Column
    val img: String,

    @Enumerated(EnumType.STRING)
    @Column
    val role: UserRole,

    @Enumerated(EnumType.STRING)
    @Column
    val authProvider: AuthProvider,

    @Column
    val refreshToken: String?,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE])
    var analysis: MutableList<Analysis> = mutableListOf(),

    ) {
    init{
        if(username.isBlank()){
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다.")
        }
    }

    fun updateUsername(name: String){
        this.username = name
    }

    companion object{
        fun newInstance(
            username: String,
            id: Long? = null,
            email: String,
            img: String,
            role: UserRole,
            refreshToken: String? = null,
            authProvider: AuthProvider,
        ): User{
            return User(id, username, email,img,role,authProvider,refreshToken)
        }
    }

}