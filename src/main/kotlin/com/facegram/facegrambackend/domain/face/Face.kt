package com.facegram.facegrambackend.domain.face

import com.facegram.facegrambackend.domain.analyze.Analysis
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class Face constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val type: String,

    @Column
    val size: String,

    @Column
    val foreheadType: String,

    @Column
    val foreheadSize: String?,

    @Column
    val chinType: String?,

    @Column
    val chinSize: String?,

    @Column
    val cheek: String?,

    @OneToOne(mappedBy = "face")
    val analysis: Analysis

) {

    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            type: String,
            size: String,
            foreheadType: String,
            foreheadSize: String? = null,
            chinType: String? = null,
            chinSize: String? = null,
            cheek: String? = null,
            analysis: Analysis
        ): Face {
            return Face(
                id,
                type,
                size,
                foreheadType,
                foreheadSize,
                chinType,
                chinSize,
                cheek,
                analysis
            )
        }
    }
}