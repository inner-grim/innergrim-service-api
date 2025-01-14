package com.team.innergrim.innergrimapi.batch

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

@Component
@EnableBatchProcessing
class SampleJob {

    @Bean
    fun samplePrintJob(
        jobRepository: JobRepository,
        samplePrintStep1: Step
    ): Job {
        return JobBuilder("samplePrintJob", jobRepository)
            .start(samplePrintStep1)
            .build()
    }

    @Bean
    fun samplePrintStep1(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder("samplePrintStep1", jobRepository)
            .tasklet({ contribution, chunkContext ->
                println("hello world job!!!")
                RepeatStatus.FINISHED
            }, transactionManager)
            .build()
    }
}