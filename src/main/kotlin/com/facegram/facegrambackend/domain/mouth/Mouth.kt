package com.facegram.facegrambackend.domain.mouth

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.hairstyle.Hairstyle
import javax.persistence.*


@Entity
class Mouth constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val type: String,

    @Column
    val size: String,

    @Column
    val thick: String?,

    @Column
    val ratio: String?,

    @Column
    val side: String?,

    @Column
    val line: String?,
) {
    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            type: String,
            size: String,
            thick: String? = null,
            ratio: String? = null,
            side: String? = null,
            line: String? = null,
        ): Mouth {
            return Mouth(
                id,
                type,
                size,
                thick,
                ratio,
                side,
                line,
            )
        }
    }
}