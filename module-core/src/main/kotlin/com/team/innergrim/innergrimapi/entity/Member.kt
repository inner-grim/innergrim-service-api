package com.team.innergrim.innergrimapi.entity

import com.team.innergrim.innergrimapi.base.BaseEntity
import com.team.innergrim.innergrimapi.enums.*
import com.team.innergrim.innergrimapi.utils.DateTimeUtils
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "member")
class Member() : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    lateinit var role: Role

    @Comment("로그인 ID")
    @Column(name = "login_id", nullable = false, length = 255)
    lateinit var loginId: String

    @Comment("로그인 비밀번호")
    @Column(name = "password", nullable = false, length = 255)
    lateinit var password: String

    @Enumerated(EnumType.STRING)
    @Comment("멤버 type")
    @Column(name = "member_type", nullable = false, length = 20)
    lateinit var memberType: MemberType

    @Enumerated(EnumType.STRING)
    @Comment("소셜 type")
    @Column(name = "social_type", nullable = false, length = 20)
    lateinit var socialType: SocialType

    @Comment("이름")
    @Column(name = "name", nullable = true, length = 255)
    var name: String? = null

    @Comment("이메일")
    @Column(name = "email", nullable = true, length = 255)
    var email: String? = null

    @Comment("생년월일")
    @Column(name = "birth_date", nullable = true, length = 8)
    var birthDate: String? = null

    @Comment("전화번호")
    @Column(name = "phone_number", nullable = true, length = 20)
    var phoneNumber: String? = null

    @Comment("ci")
    @Column(name = "ci", nullable = true, length = 255)
    var ci: String? = null

    @Comment("닉네임")
    @Column(name = "nickname", nullable = true, length = 255)
    var nickname: String? = null

    @Enumerated(EnumType.STRING)
    @Comment("성별")
    @Column(name = "gender", nullable = true, length = 20)
    var gender: Gender? = null

    @Comment("프로필 이미지")
    @Column(name = "profile_image", nullable = true, length = 255)
    var profileImage: String? = null

    @Enumerated(EnumType.STRING)
    @Comment("멤버 상태")
    @Column(name = "member_status", nullable = false, length = 50)
    lateinit var memberStatus: MemberStatus

    fun delete (memberStatus: MemberStatus) {
        this.loginId = "${DateTimeUtils.getDateTimeNow(DateTimeUtils.FORMAT_yyyyMMddHHmmss)}_${memberStatus.name}_${loginId}"
        this.memberStatus = memberStatus
        this.status = Status.deleted
    }
}