package com.facegram.facegrambackend.domain.analyze

import com.facegram.facegrambackend.domain.characteristic.Characteristic
import com.facegram.facegrambackend.domain.eye.Eye
import com.facegram.facegrambackend.domain.eyebrows.Eyebrows
import com.facegram.facegrambackend.domain.face.Face
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
    var name: String,

    @ManyToOne
    val user: User,

    @OneToOne
    @JoinColumn(name = "face_id")
    val face: Face,

    @OneToOne
    @JoinColumn(name = "hairstyle_id")
    val hairstyle: Hairstyle,

    @OneToOne
    @JoinColumn(name = "eyebrows_id")
    val eyebrows: Eyebrows,

    @OneToOne
    @JoinColumn(name = "eye_id")
    val eye: Eye,

    @OneToOne
    @JoinColumn(name = "nose_id")
    val nose: Nose,

    @OneToOne
    @JoinColumn(name = "mouth_id")
    val mouth: Mouth,

    @OneToOne
    @JoinColumn(name = "wrinkle_id")
    val wrinkle: Wrinkle,

    @OneToOne
    @JoinColumn(name = "characteristic_id")
    val characteristic: Characteristic,

    @OneToOne
    @JoinColumn(name = "impression_id")
    val impression: Impression,

    @Column
    var description: String?,

    @Column
    var age: Int,

    @Column
    var gender: String,

    @Column
    var image: String?,


    ) {
    companion object{
        fun newInstance(
            id: Long? = null,
            name: String = "몽타주 이름",
            user: User,
            face: Face,
            hairstyle: Hairstyle,
            eyebrows: Eyebrows,
            eye: Eye,
            nose: Nose,
            mouth: Mouth,
            wrinkle: Wrinkle,
            characteristic: Characteristic,
            impression: Impression,
            description: String? = null,
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
                eye,
                nose,
                mouth,
                wrinkle,
                characteristic,
                impression,
                description,
                age,
                gender,
                image)
        }
    }
}