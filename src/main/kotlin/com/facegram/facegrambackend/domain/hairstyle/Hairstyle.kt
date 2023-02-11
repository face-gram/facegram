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
    val hairType: String,

    @Column
    val topLength: String,

    @Column
    val sideLength: String,

    @Column
    val part: String?,

    @OneToOne(mappedBy = "hairstyle")
    val analysis: Analysis
) {
    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            hairType: String,
            topLength: String,
            sideLength: String,
            part: String? = null,
            analysis: Analysis
        ): Hairstyle {
            return Hairstyle(
                id,
                hairType,
                topLength,
                sideLength,
                part,
                analysis
            )
        }
    }
}