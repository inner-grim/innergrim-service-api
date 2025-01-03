package com.team.innergrim.innergrimapi.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class BatchScheduler (
    private val jobLauncher: JobLauncher,
    private val samplePrintJob: Job
) {

//    @Scheduled(cron = "0/30 * * * * ?")
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

//    @Scheduled
//    fun sendDailyReportDiscord () {
//        // TODO
//    }

}