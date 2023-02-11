package com.facegram.facegrambackend.domain.eye

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.characteristic.Characteristic
import javax.persistence.*


@Entity
class Eye constructor(

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
    val slent: String?,

    @Column
    val shape: String?,

    @Column
    val eyeLids: String?,

    @Column
    val bottom: String?,

    @OneToOne(mappedBy = "eye")
    val analysis: Analysis
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
            analysis: Analysis
        ): Eye {
            return Eye(
                id,
                size,
                type,
                distance,
                slent,
                shape,
                eyeLids,
                bottom,
                analysis
            )
        }
    }
}