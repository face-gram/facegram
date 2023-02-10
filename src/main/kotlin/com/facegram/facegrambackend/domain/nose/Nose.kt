package com.facegram.facegrambackend.domain.nose

import com.facegram.facegrambackend.domain.analyze.Analysis
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
    val heights: String,

    @Column
    val top: String,

    @Column
    val nose_trills: String,

    @Column
    val philtrum: String,

    @OneToOne(mappedBy = "nose")
    val analysis: Analysis
){
}