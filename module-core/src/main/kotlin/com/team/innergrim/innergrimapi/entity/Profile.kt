package com.team.innergrim.innergrimapi.entity

import com.team.innergrim.innergrimapi.base.BaseEntity
import com.team.innergrim.innergrimapi.enums.Gender
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "profile")
class Profile() : BaseEntity() {

    @Comment("닉네임")
    @Column(name = "nick_name", nullable = true, length = 255)
    var nickName: String? = null

    @Enumerated(EnumType.STRING)
    @Comment("성별")
    @Column(name = "gender", nullable = true, length = 20)
    var gender: Gender? = null

    @Comment("프로필 이미지")
    @Column(name = "profile_image", nullable = true, length = 255)
    var profileImage: String? = null

}