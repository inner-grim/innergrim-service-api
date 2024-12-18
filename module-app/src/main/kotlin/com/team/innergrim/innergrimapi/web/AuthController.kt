package com.team.innergrim.innergrimapi.web

import com.team.innergrim.innergrimapi.response.BaseResponse
import com.team.innergrim.innergrimapi.service.AuthService
import com.team.innergrim.innergrimapi.web.dto.AuthRequestDto
import com.team.innergrim.innergrimapi.web.dto.AuthResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth", description = "인증/인가 API")
@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService
) {

    // ::::: [GET] :::::

    // ::::: [POST] :::::
    @Operation(summary = "AccessToken 검증", description = "AccessToken 검증")
    @PostMapping("/validate/access-token")
    fun validateAccessToken(@RequestBody @Valid validateAccessTokenDto: AuthRequestDto.ValidateAccessToken): BaseResponse<Unit> {
        authService.validateAccessToken(validateAccessTokenDto)
        return BaseResponse.successWithoutData()
    }

    @Operation(summary = "AccessToken 발급", description = "AccessToken 발급")
    @PostMapping("/issue/access-token")
    fun issueAccessToken(@RequestBody @Valid issueAccessTokenDto: AuthRequestDto.IssueAccessToken): BaseResponse<Unit> {
        authService.issueAccessToken(issueAccessTokenDto)
        return BaseResponse.successWithoutData()
    }

    @Operation(summary = "회원 로그인", description = "회원 로그인")
    @PostMapping("/member/login")
    fun memberLogin(@RequestBody @Valid memberLoginDto : AuthRequestDto.MemberLogin): BaseResponse<AuthResponseDto.MemberLogin> {
        return BaseResponse.successWithData(authService.memberLogin(memberLoginDto))
    }

    @Operation(summary = "관리자 로그인", description = "관리자 로그인")
    @PostMapping("/admin/login")
    fun adminLogin(@RequestBody @Valid adminLoginDto : AuthRequestDto.AdminLogin): BaseResponse<AuthResponseDto.AdminLogin> {
        return BaseResponse.successWithData(authService.adminLogin(adminLoginDto))
    }

}