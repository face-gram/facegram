package com.facegram.facegrambackend.domain.impression

import com.facegram.facegrambackend.domain.analyze.Analysis
import javax.persistence.*


@Entity
class Impression constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val type: String,

    @OneToOne(mappedBy = "impression")
    val analysis: Analysis
){
}