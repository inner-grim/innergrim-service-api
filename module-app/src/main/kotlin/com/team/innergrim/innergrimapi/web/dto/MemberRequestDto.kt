package com.team.innergrim.innergrimapi.web.dto

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.entity.Membership
import com.team.innergrim.innergrimapi.enums.SocialType
import com.team.innergrim.innergrimapi.enums.Gender
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class MemberRequestDto {

    data class Create(
        @NotNull
        val socialType: SocialType,
        @NotBlank
        val socialId: String,
        @NotBlank
        val name: String,
        @NotBlank
        val email: String,
        @NotBlank
        val birthDate: String,
        @NotNull
        val gender: Gender,
        @NotBlank
        val phoneNumber: String,
        @NotBlank
        val ci: String,

        val profileImage: String
    ) {
        fun toMemberEntity(membership: Membership):Member {
            return Member().apply {
                this.socialType = this@Create.socialType
                this.membership = membership
                this.socialId = this@Create.socialId
                this.name = this@Create.name
                this.email = this@Create.email
                this.birthDate = this@Create.birthDate
                this.gender = this@Create.gender
                this.phoneNumber = this@Create.phoneNumber
                this.ci = this@Create.ci
                this.profileImage = this@Create.profileImage
            }
        }
    }
}