package com.team.innergrim.innergrimapi.cms.service

import com.team.innergrim.innergrimapi.cms.web.dto.StatisticsRequestDto
import com.team.innergrim.innergrimapi.cms.web.dto.StatisticsResponseDto
import com.team.innergrim.innergrimapi.dto.SearchMemberDto
import com.team.innergrim.innergrimapi.dto.SearchPictureDiaryDto
import com.team.innergrim.innergrimapi.enums.MemberStatus
import com.team.innergrim.innergrimapi.enums.MemberType
import com.team.innergrim.innergrimapi.service.MemberDomainService
import com.team.innergrim.innergrimapi.service.PictureDiaryDomainService
import org.springframework.stereotype.Service

@Service
class CmsStatisticsService(
    private val memberDomainService: MemberDomainService,
    private val pictureDiaryDomainService: PictureDiaryDomainService
) {

    fun getMemberStatistics (memberStatisticsRequestDto: StatisticsRequestDto.Member): StatisticsResponseDto.Member {
        // 가입 회원 조회
        val createdMemberList = memberDomainService.getMembers(
            SearchMemberDto(
                memberType = MemberType.user,
                memberStatus = MemberStatus.normal,
                createStartDateAt = memberStatisticsRequestDto.startDate,
                createEndDateAt = memberStatisticsRequestDto.endDate
            ).specification
        )
        // 탈퇴 회원 조회
        val deletedMemberList = memberDomainService.getMembers(
            SearchMemberDto(
                memberType = MemberType.user,
                memberStatus = MemberStatus.withdraw,
                updateStartDateAt = memberStatisticsRequestDto.startDate,
                updateEndDateAt = memberStatisticsRequestDto.endDate
            ).specification
        )

        return StatisticsResponseDto.Member(
            createdMemberCount = createdMemberList.size,
            deletedMemberCount = deletedMemberList.size
        )
    }

    fun getPictureDiaryStatistics(pictureDiaryStatisticsRequestDto: StatisticsRequestDto.PictureDiary): StatisticsResponseDto.PictureDiary {
        // 생성한 그림일기 조회
        val createdPictureDiaryList = pictureDiaryDomainService.getPictureDiaries(
            SearchPictureDiaryDto(
                createStartDateAt = pictureDiaryStatisticsRequestDto.startDate,
                createEndDateAt = pictureDiaryStatisticsRequestDto.endDate
            ).specification
        )

        return StatisticsResponseDto.PictureDiary(
            createdPictureDiaryCount = createdPictureDiaryList.size
        )
    }

}