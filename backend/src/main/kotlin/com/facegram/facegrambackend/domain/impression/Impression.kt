package com.facegram.facegrambackend.domain.impression

import com.facegram.facegrambackend.domain.analyze.Analysis
import com.facegram.facegrambackend.domain.hairstyle.Hairstyle
import javax.persistence.*


@Entity
class Impression constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val type: String?,

){
    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            type: String? = null,
        ): Impression {
            return Impression(
                id,
                type,
            )
        }
    }
}