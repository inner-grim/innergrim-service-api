package com.team.innergrim.innergrimapi.web

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.response.BaseResponse
import com.team.innergrim.innergrimapi.service.MemberService
import com.team.innergrim.innergrimapi.web.dto.MemberRequestDto
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member")
@RequestMapping("/member")
@RestController
class MemberController(
    private val memberService: MemberService
) {

    // ::::: [GET] :::::

    @GetMapping
    fun getMembers(): List<Member> {
        return memberService.getMembers()
    }

    @GetMapping("/{id}")
    fun getMemberDetail(@PathVariable("id") id: Long): Member {
        return memberService.getMemberDetail(id)
    }

    // ::::: [POST] :::::
    @PostMapping
    fun createMember(@RequestBody @Valid createMemberRequestDto : MemberRequestDto.Create): BaseResponse<Unit> {
        memberService.createMember(createMemberRequestDto)
        return BaseResponse.successWithoutData()
    }

}