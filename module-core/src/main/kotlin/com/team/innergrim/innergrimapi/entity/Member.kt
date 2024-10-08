package com.team.innergrim.innergrimapi.entity

import com.team.innergrim.innergrimapi.base.BaseEntity
import com.team.innergrim.innergrimapi.enums.Gender
import com.team.innergrim.innergrimapi.enums.SocialType
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "member")
class Member() : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id", nullable = false)
    lateinit var membership: Membership

    @Enumerated(EnumType.STRING)
    @Comment("소셜 type")
    @Column(name = "social_type", nullable = false, length = 20)
    lateinit var socialType: SocialType

    @Comment("소셜 ID")
    @Column(name = "social_id", nullable = false, length = 255)
    lateinit var socialId: String

    @Comment("이름")
    lateinit var name: String

    @Comment("이메일")
    @Column(name = "email", nullable = false, length = 255)
    lateinit var email: String

    @Comment("생년월일")
    @Column(name = "birth_date", nullable = false, length = 8)
    lateinit var birthDate: String

    @Enumerated(EnumType.STRING)
    @Comment("성별")
    @Column(name = "gender", nullable = false, length = 20)
    lateinit var gender: Gender

    @Comment("전화번호")
    @Column(name = "phone_number", nullable = false, length = 20)
    lateinit var phoneNumber: String

    @Comment("ci")
    @Column(name = "ci", nullable = false, length = 255)
    lateinit var ci: String

    @Comment("프로필 이미지")
    @Column(name = "profile_image", nullable = true, length = 255)
    var profileImage: String? = null

}