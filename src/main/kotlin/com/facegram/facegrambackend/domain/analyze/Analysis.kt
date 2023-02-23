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
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@NoArgsConstructor
class Analysis constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    private var name: String,

    @ManyToOne
    private val user: User,

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "face_id")
    private val face: Face,

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "hairstyle_id")
    private val hairstyle: Hairstyle,

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "eyebrows_id")
    private val eyebrows: Eyebrows,

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "eyes_id")
    private val eyes: Eyes,

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "nose_id")
    private val nose: Nose,

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "mouth_id")
    private val mouth: Mouth,

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "wrinkle_id")
    private val wrinkle: Wrinkle,

//    @OneToOne(cascade = [CascadeType.REMOVE])
//    @JoinColumn(name = "characteristic_id")
//    private val characteristic: Characteristic,

    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "feature_id")
    private val feature: Feature,


    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "impression_id")
    private val impression: Impression,

    @Column
    private var description: String?,

    @Column
    private val age: Int,

    @Column
    private val gender: String,

    @Column
    var image: String?,


    ): BaseTimeEntity() {
    companion object{
        fun newInstance(
            id: Long? = null,
            name: String = "몽타주 이름",
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
            description: String? = "몽타주 설명입니다.",
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