package com.facegram.facegrambackend.domain.hairstyle

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.face.Face
import javax.persistence.*


@Entity
class Hairstyle constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val type: String,

    @Column
    val topLength: String,

    @Column
    val sizeLength: String,

    @Column
    val part: String?,

) {
    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            hairType: String,
            topLength: String,
            sizeLength: String,
            part: String? = null,
        ): Hairstyle {
            return Hairstyle(
                id,
                hairType,
                topLength,
                sizeLength,
                part,
            )
        }
    }
}