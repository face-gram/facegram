package com.facegram.facegrambackend.domain.analyze

import com.facegram.facegrambackend.domain.BaseTimeEntity
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
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
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

    @OneToOne
    @JoinColumn(name = "face_id")
    private val face: Face,

    @OneToOne
    @JoinColumn(name = "hairstyle_id")
    private val hairstyle: Hairstyle,

    @OneToOne
    @JoinColumn(name = "eyebrows_id")
    private val eyebrows: Eyebrows,

    @OneToOne
    @JoinColumn(name = "eye_id")
    private val eye: Eye,

    @OneToOne
    @JoinColumn(name = "nose_id")
    private val nose: Nose,

    @OneToOne
    @JoinColumn(name = "mouth_id")
    private val mouth: Mouth,

    @OneToOne
    @JoinColumn(name = "wrinkle_id")
    private val wrinkle: Wrinkle,

    @OneToOne
    @JoinColumn(name = "characteristic_id")
    private val characteristic: Characteristic,

    @OneToOne
    @JoinColumn(name = "impression_id")
    private val impression: Impression,

    @Column
    private var description: String?,

    @Column
    private val age: Int,

    @Column
    private val gender: String,

    @Column
    private var image: String?,


    ): BaseTimeEntity() {
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