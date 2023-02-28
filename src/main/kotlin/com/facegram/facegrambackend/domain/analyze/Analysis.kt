package com.facegram.facegrambackend.domain.analyze

import com.facegram.facegrambackend.domain.BaseTimeEntity
import com.facegram.facegrambackend.domain.characteristic.Characteristic
import com.facegram.facegrambackend.domain.eyes.Eyes
import com.facegram.facegrambackend.domain.eyebrows.Eyebrows
import com.facegram.facegrambackend.domain.face.Face
import com.facegram.facegrambackend.domain.feature.Feature
import com.facegram.facegrambackend.domain.hairstyle.Hairstyle
import com.facegram.facegrambackend.domain.impression.Impression
import com.facegram.facegrambackend.domain.mouth.Mouth
import com.facegram.facegrambackend.domain.nose.Nose
import com.facegram.facegrambackend.domain.user.User
import com.facegram.facegrambackend.domain.wrinkle.Wrinkle
import lombok.Getter
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@NoArgsConstructor
class Analysis constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var name: String,

    @ManyToOne
    val user: User,

    @OneToOne(cascade = [CascadeType.REMOVE], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "face_id")
    val face: Face,

    @OneToOne(cascade = [CascadeType.REMOVE], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "hairstyle_id")
    val hairstyle: Hairstyle,

    @OneToOne(cascade = [CascadeType.REMOVE], orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "eyebrows_id")
    val eyebrows: Eyebrows,

    @OneToOne(cascade = [CascadeType.REMOVE], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "eyes_id")
    val eyes: Eyes,

    @OneToOne(cascade = [CascadeType.REMOVE], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "nose_id")
    val nose: Nose,

    @OneToOne(cascade = [CascadeType.REMOVE], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "mouth_id")
    val mouth: Mouth,

    @OneToOne(cascade = [CascadeType.REMOVE], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "wrinkle_id")
    val wrinkle: Wrinkle,

//    @OneToOne(cascade = [CascadeType.REMOVE])
//    @JoinColumn(name = "characteristic_id")
//    private val characteristic: Characteristic,

    @OneToOne(cascade = [CascadeType.REMOVE], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id")
    val feature: Feature,


    @OneToOne(cascade = [CascadeType.REMOVE], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "impression_id")
    val impression: Impression,

    @Column
    var description: String,

    @Column
    val age: Int,

    @Column
    val gender: String,

    @Column
    var image: String?,


    ): BaseTimeEntity() {
    companion object{
        fun newInstance(
            id: Long? = null,
            name: String,
            user: User,
            face: Face,
            hairstyle: Hairstyle,
            eyebrows: Eyebrows,
            eyes: Eyes,
            nose: Nose,
            mouth: Mouth,
            wrinkle: Wrinkle,
//            characteristic: Characteristic,
            feature: Feature,
            impression: Impression,
            description: String,
            age: Int,
            gender: String,
            image: String? = null,

            ): Analysis{
            return Analysis(id,
                name,
                user,
                face,
                hairstyle,
                eyebrows,
                eyes,
                nose,
                mouth,
                wrinkle,
//                characteristic,
                feature,
                impression,
                description,
                age,
                gender,
                image)
        }
    }
}