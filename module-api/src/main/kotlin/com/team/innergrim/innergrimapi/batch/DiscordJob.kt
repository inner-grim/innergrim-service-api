package com.team.innergrim.innergrimapi.batch

import com.team.innergrim.innergrimapi.cms.service.CmsStatisticsService
import com.team.innergrim.innergrimapi.cms.web.dto.StatisticsRequestDto
import com.team.innergrim.innergrimapi.dto.ExternalApiRequestDto
import com.team.innergrim.innergrimapi.external.service.DiscordService
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import java.time.LocalDate

@Component
@EnableBatchProcessing
class DiscordJob (
    private val cmsStatisticsService: CmsStatisticsService,
    private val discordService: DiscordService
)  {

    @Bean
    fun sendDailyReportDiscordJob(
        jobRepository: JobRepository,
        sendDailyReportDiscordJobStep1: Step
    ): Job {
        return JobBuilder("sendDailyReportDiscordJob", jobRepository)
            .start(sendDailyReportDiscordJobStep1)
            .build()
    }

    @Bean
    fun sendDailyReportDiscordJobStep1(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder("sendDailyReportDiscordJobStep1", jobRepository)
            .tasklet({ contribution, chunkContext ->
                val requestDate = LocalDate.now().minusDays(1)

                val memberStatisticsResponseDto = cmsStatisticsService.getMemberStatistics(
                    StatisticsRequestDto.Member(
                        startDate = requestDate,
                        endDate = requestDate
                    )
                )
                val pictureStatisticsResponseDto = cmsStatisticsService.getPictureDiaryStatistics(
                    StatisticsRequestDto.PictureDiary(
                        startDate = requestDate,
                        endDate = requestDate
                    )
                )

                discordService.sendDiscordMessage(
                    ExternalApiRequestDto.SendDiscordMessage(
                        content = "",
                        username = "Innergrim-Statistics-Notification",
                        embeds = listOf(
                            ExternalApiRequestDto.SendDiscordMessage.Embed(
                                title = "Innergrim Statistics Sample",
                                description = """
                                    - 기준 일자 : ${requestDate}
                                    - 회원 가입 : ${memberStatisticsResponseDto.createdMemberCount}
                                    - 회원 탈퇴 : ${memberStatisticsResponseDto.deletedMemberCount}
                                    - 생성한 그림일기 : ${pictureStatisticsResponseDto.createdPictureDiaryCount}
                                """.trimIndent(),
                                color = 0x00FF00 // Green color
                            )
                        )
                    )
                )
                RepeatStatus.FINISHED
            }, transactionManager)
            .build()
    }
}