package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.enums.UploadType
import com.team.innergrim.innergrimapi.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class S3Service(
    private val s3Client: S3Client
) {
    fun uploadFile(multipartFile: MultipartFile, uploadType: UploadType): String {

        val originalFileName = multipartFile.originalFilename ?: ""
        val extension = originalFileName.substringAfterLast('.', "")

        val fileName = UUID.randomUUID().toString()
        val uploadDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
        val baseImageUrl = "https://dev-innergrim.s3.ap-northeast-2.amazonaws.com"

        try {
            val putObjectRequest = PutObjectRequest.builder()
                .bucket("dev-innergrim")
                .contentType(multipartFile.getContentType())
                .contentLength(multipartFile.getSize())
                .key("${uploadType.name}/${uploadDate}/${fileName}.${extension}")
                .build()
            val requestBody = RequestBody.fromBytes(multipartFile.getBytes())

            s3Client.putObject(putObjectRequest, requestBody)
        } catch (e: Exception) {
            throw BusinessException(ErrorCode.FILE_UPLOAD_FAIL, uploadType.name)
        }
        return "${baseImageUrl}/${uploadType.name}/${uploadDate}/${fileName}.${extension}"
    }

}