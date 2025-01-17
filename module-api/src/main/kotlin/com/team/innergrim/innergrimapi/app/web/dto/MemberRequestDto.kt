package com.team.innergrim.innergrimapi.app.web.dto

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.entity.Role
import com.team.innergrim.innergrimapi.enums.Gender
import com.team.innergrim.innergrimapi.enums.MemberStatus
import com.team.innergrim.innergrimapi.enums.MemberType
import com.team.innergrim.innergrimapi.enums.SocialType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class MemberRequestDto {

    data class CreateMember(
        @field:Schema(description = "소셜 type", required = true)
        @field:NotNull
        val socialType: SocialType,

        @field:Schema(description = "로그인 ID", required = true)
        @field:NotBlank
        val loginId: String,

        @field:Schema(description = "이름", required = false)
        val name: String? = null,

        @field:Schema(description = "이메일", required = false)
        val email: String? = null,

        @field:Schema(description = "생년 월일", required = false)
        val birthDate: String? = null,

        @field:Schema(description = "전화번호", required = false)
        val phoneNumber: String? = null,

        @field:Schema(description = "CI", required = false)
        val ci: String? = null,

        @field:Schema(description = "프로필 이미지", required = false)
        val profileImage: String? = null
    ) {
        fun toMemberEntity(role: Role, encodedPassword: String):Member {
            return Member().apply {
                this.socialType = this@CreateMember.socialType
                this.memberType = MemberType.user
                this.role = role
                this.loginId = this@CreateMember.loginId
                this.name = this@CreateMember.name
                this.email = this@CreateMember.email
                this.birthDate = this@CreateMember.birthDate
                this.phoneNumber = this@CreateMember.phoneNumber
                this.ci = this@CreateMember.ci
                this.memberStatus = MemberStatus.normal
                this.password = encodedPassword
            }
        }
    }

    data class CreateOnBoarding(

        @field:Schema(description = "닉네임", required = true)
        @NotBlank
        val nickname: String,

        @field:Schema(description = "성별", required = false)
        val gender: Gender? = null,
    ) {
        fun updateMemberEntity(member: Member): Member {
            member.nickname = this@CreateOnBoarding.nickname
            member.gender = this@CreateOnBoarding.gender
            return member
        }
    }

    data class DuplicateNickname(
        @field:Schema(description = "닉네임", required = true)
        @NotBlank
        val nickname: String,
    ) {}
}