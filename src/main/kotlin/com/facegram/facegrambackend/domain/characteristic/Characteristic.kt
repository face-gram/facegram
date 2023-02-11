package com.facegram.facegrambackend.domain.characteristic

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.face.Face
import javax.persistence.*


@Entity
class Characteristic constructor(

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
    val spots: String?,

    @Column
    val tattoo: String?,

    @Column
    val makeup: String?,

    @OneToOne(mappedBy = "characteristic")
    val analysis: Analysis
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
            spots: String? = null,
            tattoo: String? = null,
            makeup: String? = null,
            analysis: Analysis
        ): Characteristic {
            return Characteristic(
                id,
                mustache,
                sideburns,
                dimple,
                scar,
                mole,
                spots,
                tattoo,
                makeup,
                analysis
            )
        }
    }
}