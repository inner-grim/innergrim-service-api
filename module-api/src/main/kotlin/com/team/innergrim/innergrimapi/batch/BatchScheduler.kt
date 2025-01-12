package com.team.innergrim.innergrimapi.batch

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

//    @Scheduled(cron = "0/5 * * * * ?")
//    fun helloWorldJob () {
//        try {
//            println("Triggering samplePrintJob...")
//            jobLauncher.run(
//                samplePrintJob,
//                JobParametersBuilder()
//                .addLong("time", System.currentTimeMillis())
//                .toJobParameters()
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//    }

//    @Scheduled(cron = "0 0/5 * * * ?", zone = "Asia/Seoul")
    @Scheduled(cron = "0 0 9 * * ?", zone = "Asia/Seoul")
    fun sendDailyReportDiscord () {
        try {
            println("Triggering sendDailyReportDiscord...")
            jobLauncher.run(
                sendDailyReportDiscordJob,
                JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}