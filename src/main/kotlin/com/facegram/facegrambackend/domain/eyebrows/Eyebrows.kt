package com.facegram.facegrambackend.domain.eyebrows

import com.facegram.facegrambackend.domain.analyze.Analysis
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne


@Entity
class Eyebrows constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val type: String,

    @Column
    val deep: String,

    @Column
    val length: String?,

    @Column
    val thick: String?,

    @Column
    val glabella: String?,
) {
    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            type: String,
            deep: String,
            length: String? = null,
            thick: String? = null,
            glabella: String? = null,
        ): Eyebrows {
            return Eyebrows(
                id,
                type,
                deep,
                length,
                thick,
                glabella,
            )
        }
    }
}