package com.team.innergrim.innergrimapi.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Service
class S3Service(
    private val s3Client: S3Client
) {

    fun uploadFile(multipartFile: MultipartFile): String {
        try {
            val putObjectRequest = PutObjectRequest.builder()
                .bucket("dev-innergrim")
                .contentType(multipartFile.getContentType())
                .contentLength(multipartFile.getSize())
                .key(multipartFile.originalFilename)
                .build()

            val requestBody = RequestBody.fromBytes(multipartFile.getBytes())

            s3Client.putObject(putObjectRequest, requestBody)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "파일이 S3에 성공적으로 업로드되었습니다."
    }

}