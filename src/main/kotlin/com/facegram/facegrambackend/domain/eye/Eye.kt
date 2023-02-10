package com.facegram.facegrambackend.domain.eye

import com.facegram.facegrambackend.domain.analyze.Analysis
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
    val distance: String,

    @Column
    val slent: String,

    @Column
    val shape: String,

    @Column
    val eye_lids: String,

    @Column
    val bottom: String,

    @OneToOne(mappedBy = "eye")
    val analysis: Analysis
) {
}