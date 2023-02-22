package com.facegram.facegrambackend.domain.wrinkle

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.nose.Nose
import javax.persistence.*


@Entity
class Wrinkle constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val forehead: String?,

    @Column
    val glabella: String?,

    @Column
    val eyes: String?,

    @Column
    val mouth: String?,

    @Column
    val cheek: String?,

    @Column
    val lip: String?,


) {
    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            forehead: String? = null,
            glabella: String? = null,
            eyes: String? = null,
            mouth: String? = null,
            cheek: String? = null,
            lip: String? = null,
        ): Wrinkle {
            return Wrinkle(
                id,
                forehead,
                glabella,
                eyes,
                mouth,
                cheek,
                lip,
            )
        }
    }
}