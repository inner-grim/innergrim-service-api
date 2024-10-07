package com.team.innergrim.innergrimapi.web.dto

import com.team.innergrim.innergrimapi.enums.AuthProvider
import com.team.innergrim.innergrimapi.enums.Gender
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class MemberRequestDto {

    data class Create(
        @NotBlank
        val name: String,
        @NotBlank
        val email: String,
        @NotBlank
        val birth: String,
        @NotNull
        val gender: Gender,
        @NotBlank
        val phoneNumber: String,
        @NotBlank
        val ci: String,
        val profileImage: String,
        @NotNull
        val authProvider: AuthProvider
    ) {}

}