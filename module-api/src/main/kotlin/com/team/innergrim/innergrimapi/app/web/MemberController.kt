package com.team.innergrim.innergrimapi.app.web

import com.team.innergrim.innergrimapi.app.service.MemberService
import com.team.innergrim.innergrimapi.app.web.dto.MemberRequestDto
import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "Member", description = "회원 관련 API")
@RequestMapping("/member")
@RestController
class MemberController(
    private val memberService: MemberService
) {

    // ::::: [GET] :::::

    @Operation(summary = "회원 리스트 조회", description = "회원 리스트 조회")
    @GetMapping
    fun getMembers(): BaseResponse<List<Member>> {
        return BaseResponse.successWithData(memberService.getMembers())
    }

    @Operation(summary = "회원 상세 조회", description = "회원 상세 조회")
    @GetMapping("/{id}")
    fun getMemberDetail(@PathVariable("id") id: Long): BaseResponse<Member> {
        return BaseResponse.successWithData(memberService.getMemberDetail(id))
    }

    @Operation(summary = "닉네임 중복 검사", description = "닉네임 중복 검사")
    @GetMapping("/duplicate/nickname")
    fun getDuplicateNickname(duplicateNicknameDto :MemberRequestDto.DuplicateNickname): BaseResponse<Boolean> {
        return BaseResponse.successWithData(memberService.getDuplicateNickname(duplicateNicknameDto))
    }


    // ::::: [POST] :::::
    @Operation(summary = "회원 생성", description = "회원 생성")
    @PostMapping
    fun createMember(@RequestBody @Valid createMemberRequestDto : MemberRequestDto.CreateMember): BaseResponse<Unit> {
        memberService.createMember(createMemberRequestDto)
        return BaseResponse.successWithoutData()
    }

    @Operation(summary = "온보딩 설정", description = "온보딩 성정")
    @PostMapping("/on-boarding")
    fun createOnBoarding(@RequestBody @Valid createMemberRequestDto: MemberRequestDto.CreateOnBoarding): BaseResponse<Unit> {
        memberService.createOnBoarding(createMemberRequestDto)
        return BaseResponse.successWithoutData()
    }

}