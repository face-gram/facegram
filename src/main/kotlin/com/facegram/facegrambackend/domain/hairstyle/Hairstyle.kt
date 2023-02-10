package com.facegram.facegrambackend.domain.hairstyle

import com.facegram.facegrambackend.domain.analyze.Analysis
import javax.persistence.*


@Entity
class Hairstyle constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val hair_type: String,

    @Column
    val top_length: String,

    @Column
    val side_length: String,

    @Column
    val part: String,

    @OneToOne(mappedBy = "hairstyle")
    val analysis: Analysis
) {
}