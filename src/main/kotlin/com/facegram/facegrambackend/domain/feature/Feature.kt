package com.facegram.facegrambackend.domain.feature

import com.facegram.facegrambackend.domain.hairstyle.Hairstyle
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Feature
constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val mustache: String,

    @Column
    val sideburns: String,

    @Column
    val dimple: String?,

    @Column
    val scar: String?,

    @Column
    val mole: String?,

    @Column
    val freckles: String?,

    @Column
    val spots: String?,

    @Column
    val tattoo: String?,

    @Column
    val makeup: String?,

    @Column
    val description: String,

) {

    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            mustache: String,
            sideburns: String,
            dimple: String? = null,
            scar: String? = null,
            mole: String? = null,
            freckles: String? = null,
            spots: String? = null,
            tattoo: String? = null,
            makeup: String? = null,
            description: String,
        ): Feature {
            return Feature(
                id,
                mustache,
                sideburns,
                dimple,
                scar,
                mole,
                freckles,
                spots,
                tattoo,
                makeup,
                description
            )
        }
    }
}