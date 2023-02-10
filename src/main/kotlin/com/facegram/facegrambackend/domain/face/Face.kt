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
    val forehead_type: String,

    @Column
    val forehead_size: String,

    @Column
    val chin_type: String,

    @Column
    val chin_size: String,

    @Column
    val cheek_type: String,

    @Column
    val cheek_size: String,

    @OneToOne(mappedBy = "face")
    val analysis: Analysis
) {

    companion object{
        fun newInstance(
            // 디폴트 파라미터
            id: Long? = null,
            forehead_type: String,
            forehead_size: String,
            chin_type: String,
            chin_size: String,
            cheek_type: String,
            cheek_size: String,
            analysis: Analysis,
        ): Face{
            return Face(
                id,
                forehead_type,
                forehead_size,
                chin_type,
                chin_size,
                cheek_type,
                cheek_size,
                analysis)
        }
    }
}