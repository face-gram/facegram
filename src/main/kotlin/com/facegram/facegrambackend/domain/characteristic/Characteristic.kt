package com.facegram.facegrambackend.domain.characteristic

import com.facegram.facegrambackend.domain.analyze.Analysis
import javax.persistence.*


@Entity
class Characteristic constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val mustache: String?,

    @Column
    val sideburns: String?,

    @Column
    val dimple: String?,

    @Column
    val scar: String?,

    @Column
    val mole: String?,

    @Column
    val spots: String?,

    @Column
    val tattoo: String?,

    @Column
    val makeup: String?,

    @OneToOne(mappedBy = "characteristic")
    val analysis: Analysis
) {
}