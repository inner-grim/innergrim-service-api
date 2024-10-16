package com.team.innergrim.innergrimapi.entity

import com.team.innergrim.innergrimapi.base.BaseEntity
import com.team.innergrim.innergrimapi.enums.Gender
import com.team.innergrim.innergrimapi.enums.SocialType
import com.team.innergrim.innergrimapi.enums.YnCode
import jakarta.persistence.*
import org.hibernate.annotations.Comment

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
    @Column(name = "name", nullable = true, length = 255)
    var name: String? = null

    @Comment("이메일")
    @Column(name = "email", nullable = true, length = 255)
    var email: String? = null

    @Comment("생년월일")
    @Column(name = "birth_date", nullable = true, length = 8)
    var birthDate: String? = null

    @Enumerated(EnumType.STRING)
    @Comment("성별")
    @Column(name = "gender", nullable = true, length = 20)
    var gender: Gender? = null

    @Comment("전화번호")
    @Column(name = "phone_number", nullable = true, length = 20)
    var phoneNumber: String? = null

    @Comment("ci")
    @Column(name = "ci", nullable = true, length = 255)
    var ci: String? = null

    @Comment("프로필 이미지")
    @Column(name = "profile_image", nullable = true, length = 255)
    var profileImage: String? = null

    @Enumerated(EnumType.STRING)
    @Comment("정지 여부")
    @Column(name = "block_yn", nullable = false, length = 1)
    lateinit var blockYn: YnCode

}