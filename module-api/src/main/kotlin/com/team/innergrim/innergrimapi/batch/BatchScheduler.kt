package com.team.innergrim.innergrimapi.batch

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class BatchScheduler (
    private val jobLauncher: JobLauncher,
    private val samplePrintJob: Job,
    private val sendDailyReportDiscordJob: Job
) {
    private val log: Logger = LoggerFactory.getLogger(BatchScheduler::class.java)

//    @Scheduled(cron = "0/5 * * * * ?")
//    fun runSamplePrintJob() {
//        runJob(samplePrintJob, "samplePrintJob")
//    }

    @Scheduled(cron = "0 0 9 * * ?", zone = "Asia/Seoul")
    fun runSendDailyReportDiscordJob() {
        runJob(sendDailyReportDiscordJob, "sendDailyReportDiscordJob")
    }

    private fun runJob(job: Job, jobName: String) {
        try {
            log.info("Running Job: $jobName")
            val jobExecution = jobLauncher.run(
                job,
                JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters()
            )
            println("Job Status: ${jobExecution.status}")
        } catch (e: Exception) {
            println("Job failed: $jobName, Error: ${e.message}")
        }
    }

}