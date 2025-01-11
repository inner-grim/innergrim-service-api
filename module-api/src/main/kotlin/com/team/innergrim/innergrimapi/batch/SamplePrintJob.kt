package com.team.innergrim.innergrimapi.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager

@Component
class SamplePrintJob  {

    @Bean
    fun job (jobRepository: JobRepository, step1: Step): Job {
        return JobBuilder("samplePrintJob", jobRepository)
            .start(step1)
            .build()
    }

    @Bean
    fun step1(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder("step1", jobRepository)
            .tasklet({ contribution, chunkContext ->
                println("hello world job!!!")
                RepeatStatus.FINISHED // 작업이 성공적으로 완료되었음을 알림
            }, transactionManager)
            .build()
    }
}