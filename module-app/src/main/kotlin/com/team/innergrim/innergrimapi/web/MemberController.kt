package com.team.innergrim.innergrimapi.web

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.response.BaseResponse
import com.team.innergrim.innergrimapi.service.MemberService
import com.team.innergrim.innergrimapi.web.dto.MemberRequestDto
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

    // ::::: [POST] :::::
    @Operation(summary = "회원 생성", description = "회원 생성")
    @PostMapping
    fun createMember(@RequestBody @Valid createMemberRequestDto : MemberRequestDto.Create): BaseResponse<Unit> {
        memberService.createMember(createMemberRequestDto)
        return BaseResponse.successWithoutData()
    }

}