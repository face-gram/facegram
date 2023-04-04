package com.facegram.facegrambackend.domain.eyes

import com.facegram.facegrambackend.domain.analyze.Analysis
import javax.persistence.*


@Entity
class Eyes constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val size: String,

    @Column
    val type: String,

    @Column
    val distance: String?,

    @Column
    val slant: String?,

    @Column
    val shape: String?,

    @Column
    val eyeLids: String?,

    @Column
    val bottom: String?,
) {
    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            size: String,
            type: String,
            distance: String? = null,
            slent: String? = null,
            shape: String? = null,
            eyeLids: String? = null,
            bottom: String? = null,
        ): Eyes {
            return Eyes(
                id,
                size,
                type,
                distance,
                slent,
                shape,
                eyeLids,
                bottom,
            )
        }
    }
}