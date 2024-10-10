package com.team.innergrim.innergrimapi.web.dto

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.entity.Membership
import com.team.innergrim.innergrimapi.enums.SocialType
import com.team.innergrim.innergrimapi.enums.Gender
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class MemberRequestDto {

    data class Create(
        @field:Schema(description = "소셜 type", required = true)
        @field:NotNull
        val socialType: SocialType,

        @field:Schema(description = "소셜 ID", required = true)
        @field:NotBlank
        val socialId: String,

        @field:Schema(description = "아름", required = true)
        @field:NotBlank
        val name: String,

        @field:Schema(description = "이메일", required = true)
        @field:NotBlank
        val email: String,

        @field:Schema(description = "생년 월일", required = true)
        @field:NotBlank
        val birthDate: String,

        @field:Schema(description = "성별", required = true)
        @field:NotNull
        val gender: Gender,

        @field:Schema(description = "전화번호", required = true)
        @field:NotBlank
        val phoneNumber: String,

        @field:Schema(description = "CI", required = true)
        @field:NotBlank
        val ci: String,

        @field:Schema(description = "프로필 이미지", required = false)
        val profileImage: String?
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