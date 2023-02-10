package com.facegram.facegrambackend.domain.mouth

import com.facegram.facegrambackend.domain.analyze.Analysis
import javax.persistence.*


@Entity
class Mouth constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val type: String,

    @Column
    val size: String,

    @Column
    val thick: String,

    @Column
    val ratio: String,

    @Column
    val side: String,

    @Column
    val line: String,

    @OneToOne(mappedBy = "mouth")
    val analysis: Analysis
) {
}