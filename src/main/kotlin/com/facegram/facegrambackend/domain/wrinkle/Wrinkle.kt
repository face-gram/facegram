package com.facegram.facegrambackend.domain.wrinkle

import com.facegram.facegrambackend.domain.analyze.Analysis
import javax.persistence.*


@Entity
class Wrinkle constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val forehead: String,

    @Column
    val glabella: String,

    @Column
    val eyes: String,

    @Column
    val mouth: String,

    @Column
    val cheek: String,

    @Column
    val lip: String,

    @Column
    val neck: String,

    @OneToOne(mappedBy = "wrinkle")
    val analysis: Analysis

) {
}