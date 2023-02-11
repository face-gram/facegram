package com.facegram.facegrambackend.domain.nose

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.hairstyle.Hairstyle
import com.facegram.facegrambackend.domain.mouth.Mouth
import javax.persistence.*


@Entity
class Nose constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val size: String,

    @Column
    val length: String,

    @Column
    val heights: String?,

    @Column
    val top: String?,

    @Column
    val noseTrills: String?,

    @Column
    val philtrum: String?,

    @OneToOne(mappedBy = "nose")
    val analysis: Analysis
){
    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            size: String,
            length: String,
            heights: String? = null,
            top: String? = null,
            noseTrills: String? = null,
            philtrum: String? = null,
            analysis: Analysis
        ): Nose {
            return Nose(
                id,
                size,
                length,
                heights,
                top,
                noseTrills,
                philtrum,
                analysis
            )
        }
    }

}